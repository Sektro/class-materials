
print('4. példa: Két osztály szétválasztása MLP-vel (Multi-Layer Perceptron)' )
#Cél: Két csoportba sorolni adatpontokat egy kis MLP (Multi-Layer Perceptron) használatával.
import tensorflow as tf
from sklearn.datasets import make_classification
from sklearn.model_selection import train_test_split

# Adatkészlet generálása (2 osztály)
X, y = make_classification(n_samples=500, n_features=2, n_classes=2, n_informative=2, n_redundant=0, n_repeated=0, random_state=42)
X = tf.convert_to_tensor(X, dtype=tf.float32)
y = tf.convert_to_tensor(y, dtype=tf.float32)

# Adatok szétválasztása tanító és teszt halmazra
X_train, X_test, y_train, y_test = train_test_split(X.numpy(), y.numpy(), test_size=0.2, random_state=42)

# Visszaalakítás TensorFlow tensorokká
X_train = tf.convert_to_tensor(X_train, dtype=tf.float32)
X_test = tf.convert_to_tensor(X_test, dtype=tf.float32)
y_train = tf.convert_to_tensor(y_train, dtype=tf.float32)
y_test = tf.convert_to_tensor(y_test, dtype=tf.float32)

# Modell létrehozása
model = tf.keras.Sequential([
    tf.keras.layers.Input(shape=(2,)),  # Bemeneti forma megadása külön Input rétegként
    tf.keras.layers.Dense(10, activation='relu'),  # 2 bemeneti jellemző
    tf.keras.layers.Dense(1, activation='sigmoid')  # Kimenet sigmoid aktivációval
])
# Loss és optimizáló
model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.01),
              loss='binary_crossentropy',
              metrics=['accuracy'])

# Tanítás
history = model.fit(X_train, y_train, epochs=100, batch_size=32, verbose=0, validation_data=(X_test, y_test))

# Modell értékelése
loss, accuracy = model.evaluate(X_test, y_test, verbose=0)
print(f"Final Loss: {loss:.4f}, Final Accuracy: {accuracy:.2f}")

# Vizualizáció
import matplotlib.pyplot as plt
import numpy as np


# Adatok és döntési határvonal megjelenítése
plt.figure(figsize=(10, 6))
plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap='coolwarm', label='Train Data')
plt.scatter(X_test[:, 0], X_test[:, 1], c=y_test, cmap='coolwarm', marker='x', label='Test Data')
plt.title('MLP Classification with TensorFlow')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()
plt.grid(True)
plt.show()


# Döntési határvonal megjelenítése
x_min, x_max = X.numpy()[:, 0].min() - 1, X.numpy()[:, 0].max() + 1
y_min, y_max = X.numpy()[:, 1].min() - 1, X.numpy()[:, 1].max() + 1
xx, yy = np.meshgrid(np.arange(x_min, x_max, 0.01), np.arange(y_min, y_max, 0.01))

Z = model.predict(np.c_[xx.ravel(), yy.ravel()])
Z = (Z > 0.5).astype(int)  # Bináris osztályozás
Z = Z.reshape(xx.shape)
plt.contourf(xx, yy, Z, alpha=0.8, cmap='coolwarm')
plt.scatter(X.numpy()[:, 0], X.numpy()[:, 1], c=y.numpy(), edgecolors='k', marker='o', s=20, label='Data')
plt.title('Decision Boundary of MLP Classifier')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()
plt.grid(True)
plt.show()


# Egy másik vizualizáció TensorFlow-val
y_pred = model.predict(X).squeeze()  # Előrejelzés a teljes adathalmazra
y_pred = tf.round(y_pred).numpy()  # Kerekítés bináris osztályozáshoz és NumPy tömbbé alakítás

plt.scatter(X.numpy()[:, 0], X.numpy()[:, 1], c=y_pred, cmap='coolwarm', edgecolors='k', alpha=0.7)
plt.title("MLP osztályozó – TensorFlow")
plt.xlabel("F1")
plt.ylabel("F2")
plt.grid(True)
plt.show()
