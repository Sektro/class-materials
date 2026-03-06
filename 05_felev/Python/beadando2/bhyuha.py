# 1.feladat
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from matplotlib import colors
data = pd.read_csv("data.csv")
import plotly.express as px
from sklearn.preprocessing import LabelEncoder

# 2.feladat
# Nézd meg az adathalmaz első néhány sorát!
print(data.head())
# Az adathalmaz alapvető információit is jelenítsd meg!
print(data.info())

# 3.feladat
# 'datum' oszlop konvertálása dátumformátummá
data['Sampling Date'] = pd.to_datetime(data['Sampling Date'])
# Vizsgáld meg a hiányzó értékeket az adatokban!
print(data.isnull().sum())

# 4.feladat
# a)
plt.figure(figsize=(12, 8))
plt.subplot(1, 2, 1) # 1 sor, 2 oszlop, 1. subplot
data['Nitrogen (mg/L)'].plot(kind='hist', color='blue', title='Nitrogen', legend='auto')
plt.xlabel('Hónapok')
plt.ylabel('Nitrogénkoncentráció (mg/L)')
# Második subplot
plt.subplot(1, 2, 2) # 1 sor, 2 oszlop, 2. subplot
data['Phosphorus (mg/L)'].plot(kind='hist', color='red', title='Phosphorus', legend='auto')
plt.xlabel('Hónapok')
plt.ylabel('Foszforkoncentráció (mg/L)')
plt.tight_layout() # Igazítás a helyes megjelenítéshez
plt.show()

# b)
sns.scatterplot(x=data['Geographical Location (Longitude)'], y=data['Geographical Location (Latitude)'], hue=data['Nitrogen (mg/L)'], palette='coolwarm')
plt.title('Nitrogén szintek geográfiai eloszlása')
plt.xlabel('Hosszúsági fok')
plt.ylabel('Szélességi fok')
plt.grid(True)
plt.show()

# c)
sns.scatterplot(x=data['Geographical Location (Longitude)'], y=data['Geographical Location (Latitude)'], hue=data['Phosphorus (mg/L)'], palette='coolwarm')
plt.title('Foszfor szintek geográfiai eloszlása')
plt.xlabel('Hosszúsági fok')
plt.ylabel('Szélességi fok')
plt.grid(True)
plt.show()

# 5.feladat
max_N = round(data['Nitrogen (mg/L)'].max())
print(max_N)
levels = range(max_N+1)
color_dict = dict(zip(levels, list(colors.cnames.values())[0:-1:10]))
fig = px.scatter_mapbox(
    data,
    lat='Geographical Location (Latitude)',  # Szélességi fok
    lon='Geographical Location (Longitude)',  # Hosszúsági fok
    color='Nitrogen (mg/L)',  # Szín a nitrogénszint alapján
    color_discrete_map=color_dict,
    size_max=15,  # Pontok maximális mérete
    zoom=3,  # Alap zoom szint
    mapbox_style='open-street-map',  # Nyílt térkép stílus
    title='Nitrogénszint mérések',
    hover_data=['State of Sewage System', 'Sampling Date', 'Phosphorus (mg/L)', 'Population']  # Hover infók
)
# Térkép megjelenítése (böngészőben nyílik meg interaktívan)
fig.show()

# 6.feladat
#sewage_count = [sum(list(map(lambda x: 1 if x == "Moderate" else 0,data['State of Sewage System']))),sum(list(map(lambda x: 1 if x == "Good" else 0,data['State of Sewage System']))),sum(list(map(lambda x: 1 if x == "Poor" else 0,data['State of Sewage System'])))] # ez nem kell, a histplot maga számolja ki
plt.figure(figsize=(6, 6))
sns.histplot(data=data,x='State of Sewage System',color='blue',legend=True)
plt.title('A szennyvízrendszer állapota')
plt.xlabel('Állapot')
plt.ylabel('Darabszám')
plt.show()

# 7.feladat
# a)
newData = []
for i in range(len(data['Nitrogen (mg/L)'])) :
  newData.append(data['Nitrogen (mg/L)'][i] + data['Phosphorus (mg/L)'][i])
data['N+P'] = newData

#b)
le = LabelEncoder()
le.fit(data['State of Sewage System'])
data['State of Sewage System'] = le.transform(data['State of Sewage System'])
print(data.head())

#c)
def is_number(value):
    try:
        float(value)
        return True
    except ValueError:
        return False
    except TypeError:
        return False
dataCourse = {}
for key, value in data.items():
    if is_number(value[0]) :
      dataCourse[key] = value
dataCourseDataFrame = pd.DataFrame(dataCourse)

# d)
# Korrelációs mátrix kiszámítása
korrelacio = dataCourseDataFrame.corr(method='pearson')
print("\nKorrelációs mátrix:")
print(korrelacio)
# Korrelációs mátrix megjelenítése
plt.figure(figsize=(8, 6))
sns.heatmap(korrelacio, annot=True, fmt='.2f', cmap='coolwarm')
plt.title('Korrelációs hőtérkép')
plt.show()

# 8.feladat
data.rename(columns={'Geographical Location (Latitude)': 'Lati',
                     'Geographical Location (Longitude)': 'Long',
                     'Sampling Date': 'SDate',
                     'Nitrogen (mg/L)': 'N',
                     'Phosphorus (mg/L)': 'P',
                     'State of Sewage System': 'SWS',}, inplace=True)
data.info()

# 9.feladat
data.describe()

# 10.feladat
data['Q'] = data['N+P'].apply(lambda x: 1 if x < 4 else (3 if x > 10 else 2))
data.head()

# 11.feladat
print(data['Q'].value_counts(ascending=True))

# 12.feladat
sns.scatterplot(x=data['Long'], y=data['Lati'], hue=data['Q'], palette='coolwarm', legend=True)
plt.title('Q értékek geográfiai eloszlása')
plt.xlabel('Hosszúsági fok')
plt.ylabel('Szélességi fok')
plt.grid(True)
plt.show()

# 13.feladat
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
from sklearn.datasets import load_iris
from sklearn.tree import plot_tree
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix

newDataDict = {}
for key, value in data.items():
    if not key == "SDate" :
      newDataDict[key] = value
newData = pd.DataFrame(newDataDict)
newData.info() # ellenőrzéshez van

# Válaszd szét az adathalmazt független (`x`) és függő (`y`) változókra!
x = newData.drop(columns=["SWS"])
y = newData['SWS']

# 14.feladat
# Oszd fel az adatokat tanuló és teszt halmazokra (80%-20% arányban)!
x_learn, x_test, y_learn, y_test = train_test_split(x, y, test_size=0.2, random_state=42)

# Hozz létre egy döntési fa modellt és tanítsd be a tanuló adatokon!
decision_tree = DecisionTreeClassifier(max_depth=3, random_state=42)
decision_tree.fit(x_learn, y_learn)

# Vizualizáld a döntési fát a Matplotlib segítségével!
plt.figure(figsize=(12, 8))
plot_tree(decision_tree, feature_names=x.columns, class_names=["Poor", "Moderate", "Good"], filled=True)
plt.show()

# 15.feladat
# Készíts predikciókat a teszt halmazon!
y_pred = decision_tree.predict(x_test)

# Számítsd ki a pontosságot!
accuracy = accuracy_score(y_test, y_pred)
print(f"Pontosság: {accuracy}")

# Generálj osztályozási riportot és konfúziós mátrixot!
print("Osztályozási Riport:")
print(classification_report(y_test, y_pred))
print("Konfúziós Mátrix:")
print(confusion_matrix(y_test, y_pred))

# 16.feladat
x = newData[["Population"]]
y = newData["N+P"]

x_learn, x_test, y_learn, y_test = train_test_split(x, y, test_size=0.2, random_state=42)

model = LinearRegression() # Lin. regr. modell létrehozása
model.fit(x, y)
# Egyenes egyenletének kiírása
print(f"Y = {model.coef_[0]:.2f} * X + {model.intercept_:.2f}")
y_predict = model.predict(x) # Előrejelzés az adatokra
# Diagram rajzolása
plt.scatter(newData['Population'], newData['N+P'], color='red', label='Eredeti adatok')
plt.plot(newData['Population'], y_predict, color='blue', label='Lineáris regresszió')
plt.xlabel('Népesség (teszt halmaz)')
plt.ylabel('N+P (teszt halmaz)')
plt.title('Lineáris regresszió - Népesség és N+P')
plt.legend()
plt.show()

# 17.feladat
# Predikció
y_predict = model.predict(x)
print("Első 5 predikció:", y_predict[:5])
print("Utolsó 5 predikció:", y_predict[-5:])

# Pontosság kiszámítása a teljes teszt adatra
accuracy = model.score(x_test, y_test) # Pontosság: score metódus
print("Pontosság:", accuracy)