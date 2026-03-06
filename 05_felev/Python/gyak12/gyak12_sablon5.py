print()
print('5. példa: egyszerű neurális hálózat létrehozása és betanítása')

import numpy as np
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Input

# Példa adatok létrehozása
np.random.seed(42)
x_np = np.random.rand(100, 1).astype(np.float32)
y_np = 4 + 3 * x_np + np.random.randn(100, 1).astype(np.float32)

# NumPy tömbök TensorFlow tensorokká alakítása
x_tf = tf.convert_to_tensor(x_np)
y_tf = tf.convert_to_tensor(y_np)

# TensorFlow modell létrehozása
model = Sequential([
    Input(shape=(1,)),  # Bemeneti forma megadása külön Input rétegként
    Dense(10, activation='relu'),  # ReLU aktivációs függvény
    Dense(1)  # Kimeneti réteg
])


# Modell fordítása
model.compile(optimizer='adam', loss='mean_squared_error')

# Modell betanítása
model.fit(x_tf, y_tf, epochs=500, verbose=0)

# Előrejelzések készítése
y_pred = model.predict(x_tf)
print('predikció',y_pred[:5])
print('valós értékek',y_np[:5])
print('Pontosság:', model.evaluate(x_tf, y_tf, verbose=0))  # a modell pontosságának kiírása a tanító adatokon

# Eredmények megjelenítése
import matplotlib.pyplot as plt
plt.scatter(x_np, y_np, color='blue', label='Valós adatok')
plt.plot(x_np, y_pred, color='red', label='Előrejelzések')
plt.xlabel('X értékek')
plt.ylabel('Y értékek')
plt.title('Neurális hálózat TensorFlow és Keras segítségével')
plt.legend()
plt.show()