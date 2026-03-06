import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
from sklearn.datasets import load_iris
iris_adatok = load_iris() # Adatok betöltése dict-be
print(iris_adatok.keys()) # Kulcsok listázása
print(iris_adatok.target_names) # Célváltozók nevei
#print(iris_adatok.data) # Adatok listázása
#print(iris_adatok.DESCR) # Az Iris adatállomány rövid leírása
adatok = pd.DataFrame(iris_adatok.data, columns=iris_adatok.feature_names)
célváltozó = pd.Series(iris_adatok.target, name="cél")
print(adatok.head()) # adatok dataframe megtekintése
print()


from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
# Adatfelosztás: tanító és teszt halmazra (arány: 80% és 20%)
x_tanító, x_teszt, y_tanító, y_teszt = train_test_split(adatok, célváltozó, test_size=0.2,
random_state=42)
# Modell inicializálása és betanítása
linearis_model = LinearRegression()
linearis_model.fit(x_tanító, y_tanító)
# Predikció
predikciók = linearis_model.predict(x_teszt) # A predict metódus alkalmazása
print("Első 5 predikció:", predikciók[:5])
print("Valós értékek:", y_teszt[:5].values)
# Pontosság kiszámítása a teljes teszt adatra
pontossag = linearis_model.score(x_teszt, y_teszt) # Pontosság: score metódus
print("Pontosság:", pontossag)
print()


from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
# Modell inicializálása és betanítása
dontesi_fa = DecisionTreeClassifier(max_depth=3, random_state=42)
dontesi_fa.fit(x_tanító, y_tanító)
# Predikció és pontosság értékelése
predikciók_fa = dontesi_fa.predict(x_teszt)
print("Első 5 predikció:", predikciók_fa[:5])
print("Valós értékek:", y_teszt[:5].values)
pontosság = accuracy_score(y_teszt, predikciók_fa)
print("Döntési fa pontossága:", pontosság)
#Megjelenítés
from sklearn.tree import plot_tree
import matplotlib.pyplot as plt
plt.figure(figsize=(8, 6))
plot_tree(dontesi_fa, feature_names=iris_adatok.feature_names,
class_names=iris_adatok.target_names, filled=True)
plt.show()
print()