import numpy as np
import pandas as pd
from sklearn.preprocessing import StandardScaler, MinMaxScaler, MaxAbsScaler, RobustScaler, Normalizer

# Példa adatok létrehozása
adatok = pd.DataFrame({'A': [1, 2, 3, 4, 5],'B': [10, 20, 30, 40, 50],'C': [100, 200, 300, 400, 500]})
adatok = pd.DataFrame({'A': [1, 2, 1, 3, 1],'B': [10, 20, 30, 40, 50],'C': [100, 200, 300, 400, 500]})
print(adatok)
# StandardScaler
standard_scaler = StandardScaler()
adatok_standard = standard_scaler.fit_transform(adatok)
print("StandardScaler:\n", adatok_standard)

# MinMaxScaler
minmax_scaler = MinMaxScaler()
adatok_minmax = minmax_scaler.fit_transform(adatok)
print("MinMaxScaler:\n", adatok_minmax)

# MaxAbsScaler
maxabs_scaler = MaxAbsScaler()
adatok_maxabs = maxabs_scaler.fit_transform(adatok)
print("MaxAbsScaler:\n", adatok_maxabs)

# RobustScaler
robust_scaler = RobustScaler()
adatok_robust = robust_scaler.fit_transform(adatok)
print("RobustScaler:\n", adatok_robust)

# Normalizer
normalizer = Normalizer()
adatok_normalized = normalizer.fit_transform(adatok)
print("Normalizer:\n", adatok_normalized)

print()

from sklearn.datasets import load_iris
import pandas as pd

# Adatok betöltése
iris_adatok = load_iris()
print(iris_adatok.keys())        # Kulcsok listázása
print(iris_adatok.target_names)  # Célváltozók nevei
#print(iris_adatok.data)          # Adatok listázása
#print(iris_adatok.DESCR)         # Adatok leírása
#print(iris_adatok.target)      # Célváltozók
adatok = pd.DataFrame(iris_adatok.data, columns=iris_adatok.feature_names)
célváltozó = pd.Series(iris_adatok.target, name="cél")
print(célváltozó.value_counts())

# Adatok megtekintése
print(adatok.head())

print()

print('Adatok skálázása a Scikit-learn StandardScaler moduljával (Standardizálás)')
from sklearn.preprocessing import StandardScaler
# Skálázó inicializálása
skala = StandardScaler()
adatok_skálázva = skala.fit_transform(adatok)   # Skálázott adatok visszaalakítása DataFrame-ként
adatok_skálázva_df = pd.DataFrame(adatok_skálázva, columns=adatok.columns)
print(adatok_skálázva_df.head())


print()

print('Lineáris regresszió ML példa')
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split

# Adatfelosztás: tanító és teszt halmaz
x_tanító, x_teszt, y_tanító, y_teszt = train_test_split(adatok, célváltozó, test_size=0.2, random_state=42)
# Modell inicializálása és betanítása
linearis_model = LinearRegression()
linearis_model.fit(x_tanító, y_tanító)
# Predikció
predikciók = linearis_model.predict(x_teszt)
print("Első 10 predikció:", predikciók[:10])
print("Valós értékek:", y_teszt[:10].values)
# Pontosság kiszámítása
pontossag = linearis_model.score(x_teszt, y_teszt)
print("Pontosság:", pontossag)
# Előrejelzés az adatokra   
y_elorejelzes = linearis_model.predict(x_teszt)
from sklearn.metrics import mean_squared_error
print("MSE:", mean_squared_error(y_teszt, predikciók))


print()

print('Döntési fa ML példa')
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score

# Modell inicializálása
dontesi_fa = DecisionTreeClassifier(max_depth=3, random_state=42)
dontesi_fa.fit(x_tanító, y_tanító)

# Predikció és pontosság értékelése
predikciók_fa = dontesi_fa.predict(x_teszt)
print("Első 5 predikció:", predikciók_fa[:10])
print("Valós értékek:", y_teszt[:10].values)
pontosság = accuracy_score(y_teszt, predikciók_fa)
print("Döntési fa pontossága:", pontosság)

#Megjelenítés
from sklearn.tree import plot_tree
import matplotlib.pyplot as plt

plt.figure(figsize=(8, 6))
plot_tree(dontesi_fa, feature_names=iris_adatok.feature_names, class_names=iris_adatok.target_names, filled=True)
plt.show()


print()

print('Lineáris regresszió példa: lakásárak előrejelzése az alapterület alapján')
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
import numpy as np
import pandas as pd

# Mintaadatok létrehozása
adatok = pd.DataFrame({
    "alapterulet": [15, 50, 60, 70, 80, 90, 100, 75, 30, 40, 45, 60, 70, 80, 90, 100, 50, 60, 70, 80, 90, 100],
    "ar":[45, 150, 180, 200, 220, 250, 290, 230, 100, 110, 140, 170, 220, 250, 270, 295, 160, 180, 210, 230, 260, 310]
})

# Független és függő változók
x = adatok[["alapterulet"]]
y = adatok["ar"]
# Adatfelosztás tanuló és teszt halmazokra
x_tanító, x_teszt, y_tanító, y_teszt = train_test_split(x, y, test_size=0.2, random_state=42)
# Lineáris regresszió inicializálása és betanítása
linearis_model = LinearRegression()
linearis_model.fit(x_tanító, y_tanító)
# Modell paraméterei
w = linearis_model.coef_[0]
b = linearis_model.intercept_
print("Meredekség (w):", w)
print("Elfogási pont (b):", b)
print("Egyenes egyenlete: Y = {:.2f} * X + {:.2f}".format(w, b))
# Predikciók
predikciók = linearis_model.predict(x_teszt)
print("Első 5 predikció:", predikciók[:5])
#print("Első 5 predikció:", predikciók[:5].round(2))      #Kerekítéssel
print("Valós értékek:", y_teszt[:5].values)

# Modell értékelése
pontossag = linearis_model.score(x_teszt, y_teszt)
print("Modell pontossága:", pontossag)

# Lineáris regresszió diagramjainak megjelenítése
import matplotlib.pyplot as plt

# Tanító adatok és a lineáris regresszió megjelenítése
plt.scatter(x_tanító, y_tanító, color='green', label='Tanító adatok')
plt.plot(x_tanító, linearis_model.predict(x_tanító), color='black', label='Lineáris regresszió')
plt.xlabel('Alapterület (tanító halmaz)')
plt.ylabel('Lakásárak (tanító halmaz)')
plt.title('Lineáris regresszió a tanító adatokra')
plt.show()

# A teljes adatok és a lineáris regresszió megjelenítése
plt.scatter(x,y, color='orange')
plt.plot(x, linearis_model.predict(x), color='blue')
plt.xlabel('Alapterület')
plt.ylabel('Lakásárak')
plt.title('Lakásárak előrejelzése lineáris regresszióval')
plt.show()

# Teszt adatok és a lineáris regresszió megjelenítése
plt.scatter(x_teszt, y_teszt, color='red', label='Tanító adatok')
plt.plot(x_teszt, linearis_model.predict(x_teszt), color='blue', label='Lineáris regresszió')
plt.xlabel('Alapterület (teszt halmaz)')
plt.ylabel('Lakásárak (teszt halmaz)')
plt.title('Lineáris regresszió a teszt adatokra')
plt.show()



print()
print('Döntési fa tenisz példa')
from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
import pandas as pd
import matplotlib.pyplot as plt
# Adatok betöltése
adatok = pd.read_csv('Tenisz_csv_uj.csv')
print(adatok.head())
# Célváltozó kiválasztása
célváltozó = adatok['play']
jellemzők = adatok.drop(columns=['play'])
# Adatfelosztás: tanító és teszt halmaz
x_tanító, x_teszt, y_tanító, y_teszt = train_test_split(jellemzők, célváltozó, test_size=0.2, random_state=42)
# Modell inicializálása és betanítása
fa_modell = DecisionTreeClassifier(criterion='entropy', max_depth=3, random_state=42)
fa_modell.fit(x_tanító, y_tanító)
# Predikció
predikciók_fa = fa_modell.predict(x_teszt)
print("Első 5 predikció:", predikciók_fa[:5])
print("Valós értékek:", y_teszt[:5].values)
# Pontosság kiszámítása
pontossag = accuracy_score(y_teszt, predikciók_fa)
print("Pontosság:", pontossag)


# Döntési fa megjelenítése
plt.figure(figsize=(8, 6))     # Csúszkákkal beállítani a képet
plot_tree(fa_modell, feature_names=jellemzők.columns, class_names=['no play','play'], filled=True)
plt.title("Döntési fa vizualizáció")
plt.show()


print()

from sklearn.datasets import load_iris
import pandas as pd

print('Nem felügyelt gépi tanulás példa: PCA')
from sklearn.datasets import load_iris
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
# Adatok betöltése
iris = load_iris()
X = iris.data
# Dimenziócsökkentés PCA-val
pca = PCA(n_components=2)
X_pca = pca.fit_transform(X)
# Főkomponensek együtthatóinak megjelenítése
components = pd.DataFrame(pca.components_, columns=iris_adatok.feature_names, index=['Főkomponens 1', 'Főkomponens 2'])
print(components)
# Adatok vizualizálása
plt.scatter(X_pca[:, 0], X_pca[:, 1], c=iris.target)
plt.xlabel('Főkomponens 1')
plt.ylabel('Főkomponens 2')
plt.title('PCA az Iris adathalmazon')
plt.show()

import numpy as np
import pandas as pd
from sklearn.tree import DecisionTreeRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error
import matplotlib.pyplot as plt

# Példa adatok létrehozása
np.random.seed(42)
x = np.sort(5 * np.random.rand(80, 1), axis=0)
y = np.sin(x).ravel()
y[::5] += 3 * (0.5 - np.random.rand(16))

# Adatok felosztása tanító és teszt halmazokra
X_train, X_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)
