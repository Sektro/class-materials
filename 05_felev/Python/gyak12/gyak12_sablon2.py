
print()
print('2. példa: egy teljes tanítási és validációs folyamat: az MNIST adathalmazon')

# MNIST adathalmazon való tanítás
# Cél: Kézírásos számok osztályozása egy egyszerű neurális hálózattal.
import tensorflow as tf
from tensorflow.keras import Sequential
from tensorflow.keras.layers import Dense
import matplotlib.pyplot as plt
import numpy as np


# Adat előkészítése
(x_train, y_train), (x_test, y_test) = tf.keras.datasets.mnist.load_data()
x_train = x_train.reshape((-1, 28 * 28)).astype('float32') / 255
x_test = x_test.reshape((-1, 28 * 28)).astype('float32') / 255
y_train = tf.keras.utils.to_categorical(y_train, 10)
y_test = tf.keras.utils.to_categorical(y_test, 10)
# Modell létrehozása
modell = Sequential([
    Dense(128, activation='relu', input_shape=(784,)),
    Dense(64, activation='relu'),
    Dense(10, activation='softmax')
])
# Modell fordítása
modell.compile(
    optimizer='adam',
    loss='categorical_crossentropy',
    metrics=['accuracy']
)
print("Modell inicializálva és lefordítva.")
# Modell tanítása
eredmeny = modell.fit(
    x_train, y_train,
    epochs=10,
    batch_size=32,
    validation_split=0.2
)
# Modell kiértékelése
teszt_veszteseg, teszt_pontossag = modell.evaluate(x_test, y_test)
print(f"Teszthalmaz vesztesége: {teszt_veszteseg}")
print(f"Teszthalmaz pontossága: {teszt_pontossag}")

# Előrejelzés új adatokra
"""
index = 0  # Példa index
x = x_test[index].reshape(1, 784)  # Az indexelt adat előkészítése
y_pred = modell.predict(x)  # Előrejelzés
print(f"Előrejelzett osztály: {np.argmax(y_pred)}")  # A legmagasabb valószínűségű osztály kiírása
print(f"Valós osztály: {np.argmax(y_test[index])}")  # A valós osztály kiírása
"""

for index in range(10):  # Az első 10 tesztadatot vizsgáljuk
    x = x_test[index].reshape(1, 784)  # Az indexelt adat előkészítése
    y_pred = modell.predict(x)  # Előrejelzés
    print(f"Index: {index}")
    print(f"Előrejelzett osztály: {np.argmax(y_pred)}")  # A legmagasabb valószínűségű osztály kiírása
    print(f"Valós osztály: {np.argmax(y_test[index])}")  # A valós osztály kiírása
    print()

    # Eredmények megjelenítése
    plt.imshow(x_test[index].reshape(28, 28), cmap='gray')
    plt.title(f"Előrejelzett osztály: {np.argmax(y_pred)}, Valós osztály: {np.argmax(y_test[index])}")
    plt.axis('off')
    plt.show()


# Loss függvény megjelenítése
plt.plot(eredmeny.history['loss'], label='Veszteség (tanító)')
plt.plot(eredmeny.history['val_loss'], label='Veszteség (validáció)')
plt.title('Veszteség függvény')
plt.xlabel('Epochok')
plt.ylabel('Veszteség')
plt.legend()
plt.show()

# Pontosság függvény megjelenítése
plt.plot(eredmeny.history['accuracy'], label='Pontosság (tanító)')
plt.plot(eredmeny.history['val_accuracy'], label='Pontosság (validáció)')
plt.title('Pontosság függvény')
plt.xlabel('Epochok')
plt.ylabel('Pontosság')
plt.legend()
plt.show()

