'''
print()
#Tensorok és műveleteik: példa 1.
print('Példa a TensorFlow tensorok használatára:')
import tensorflow as tf

# TensorFlow konstansok létrehozása
a = tf.constant([1, 2, 3], dtype=tf.float32)
b = tf.constant([4, 5, 6], dtype=tf.float32)

print("a:", a)
print("b:", b)

print()
print('Tensor műveletek')
c = a + b
d = a * b
e = 2 * a + 3 * b
f = 2**a
print("c:", c)
print("d:", d)
print("e:", e)
print("f:", f)

print()
pelda_tensor = tf.constant([[1, 2, 3], [4, 5, 6]])
print(pelda_tensor)
print()
a = tf.constant([3, 5])
b = tf.constant([4,2])
szorzat = a * b
print(szorzat)
print()
tensor_a = tf.constant([5, 10, 15], name="tensor_a")
tensor_b = tf.constant([2, 4, 6], name="tensor_b")
osszeg = tf.add(tensor_a, tensor_b)
print("Eredmény:", osszeg.numpy())

print()
print('Tensor műveletek NumPy tömbökkel')
import numpy as np
x = np.array([1, 2, 3], dtype=np.float32)
y = np.array([4, 5, 6], dtype=np.float32)

print("x:", x)
print("y:", y)

print("x + y:", x + y)
print("x * y:", x * y)
'''


'''
print()
# Cél: Egy egyszerű neurális hálózat létrehozása és összegzése TensorFlow és Keras segítségével.
print('1. példa: Egyszerű neurális háló képosztályozásra')

import tensorflow as tf
from tensorflow.keras import Sequential
from tensorflow.keras.layers import Dense, Dropout
# Modell inicializálása
modell = Sequential()
# Bemeneti réteg és első rejtett réteg hozzáadása
modell.add(Dense(128, activation='relu', input_shape=(784,), name="rejtett_reteg_1"))
# Dropout a túltanulás elkerülésére
modell.add(Dropout(0.2, name="dropout_reteg"))
# Második rejtett réteg
modell.add(Dense(64, activation='relu', name="rejtett_reteg_2"))
# Kimeneti réteg (10 osztály esetén)
modell.add(Dense(10, activation='softmax', name="kimeneti_reteg"))
# Modell összegzése
modell.summary()
print()
'''