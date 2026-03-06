
print()
print('3. példa: Tensorflow egyszerű lineáris regresszió')
# Cél: Egy lineáris függvény (pl. y = 2x + 3) modellezése.
import tensorflow as tf
from tensorflow.keras import Sequential, Input
from tensorflow.keras.layers import Dense

# Adatgenerálás (szintetikus)
X = tf.linspace(0.0, 10.0, 100)[:, tf.newaxis]  # 0 és 10 között generál 100 egyenletesen elosztott adatpontot, 1 jellemző
y = 2 * X + 3 + tf.random.normal(X.shape, mean=0.0, stddev=0.5)  # kis zaj hozzáadása a célértékekhez

# Egyszerű lineáris modell: egyetlen Dense réteggel, melynek 1 bemeneti és 1 kimeneti neuronja van
# (y = wx + b formában, ahol w a súly és b az eltolás)

# Egyszerű lineáris modell
model = Sequential([
    Input(shape=(1,)),  # Bemeneti forma megadása külön Input rétegként
    Dense(1)            # Dense réteg
])

# Loss és optimizáló
model.compile(optimizer=tf.keras.optimizers.SGD(learning_rate=0.01), # SGD (Stochastic Gradient Descent) az optimalizáló algoritmus
              loss='mean_squared_error')  # MSE (Mean Squared Error) a veszteségfüggvény, amelyet minimalizálni szeretnénk

# Tanítás
model.fit(X, y, epochs=100, verbose=0) ## 100 epochon keresztül tanítjuk a modellt, verbose=0 azt jelenti, hogy nem írunk ki részletes információt a tanítás során

# Modellparaméterek
weights, bias = model.layers[0].get_weights() # a tanult súly és az eltolás lekérdezése
print(f"Learned weight: {weights[0][0]:.2f}, bias: {bias[0]:.2f}")

print("Predictions:")
# Előrejelzés új adatokra
predictions = model.predict(X)  # a modell előrejelzése az X adatokra
print(predictions[:5])  # az első 5 előrejelzés kiírása
print(y[:5])  # az első 5 célérték kiírása
print('Veszteség:', model.evaluate(X, y, verbose=0))  # a modell pontosságának kiírása a tanító adatokon
# A mean_squared_error a négyzetes eltérések átlaga, így az érték nem egy százalékos pontosság, hanem egy numerikus hiba. Az alacsonyabb érték jobb illeszkedést jelent.


# Vizualizáció
import matplotlib.pyplot as plt     

plt.scatter(X, y, label='Data')  # az eredeti adatok pontokként való megjelenítése
plt.plot(X, predictions, color='red', label='Model')  # a modell előrejelzéseinek megjelenítése vonalként
plt.title('Simple Linear Regression with TensorFlow')  # a grafikon címe

plt.xlabel('X')  # x tengely feliratozása
plt.ylabel('y')  # y tengely feliratozása
plt.legend()  # jelmagyarázat hozzáadása
plt.show()  # a grafikon megjelenítése
