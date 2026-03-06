
print('1. példa: vonaldiagram matplotlib-bel')
import matplotlib.pyplot as plt
# Adatok: hónapok és bevételek
hónapok = ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X', 'XI', 'XII']
bevételek = [1000, 1200, 1300, 900, 1100, 1500, 1100, 1200, 1000, 900, 1700, 1400]
# Vonaldiagram készítése
plt.plot(hónapok, bevételek, marker='d', linestyle='-', color='b')
plt.title('Cég havi bevételei')
plt.xlabel('Hónapok')
plt.ylabel('Bevétel (ezer Ft)')
plt.grid(True)
plt.show()

print()

print('1.példa testreszabás:')
plt.plot(hónapok, bevételek, marker='s', linestyle='--', color='g')
plt.title('Cég havi bevételei', fontsize=16, fontweight='bold', color='red')
plt.xlabel('Hónapok', fontsize=12,  color='darkblue')
plt.ylabel('Bevétel (ezer Ft)', fontsize=12, color='darkblue')
plt.xticks(rotation=45)  # X tengely értékek elforgatása
plt.grid(color='gray', linestyle='--', linewidth=0.5)
plt.show()

print()

print('2. példa: subplot vonaldiagramok matplotlib-bel')
kiadások = [800, 900, 950, 870, 1000, 1200, 500, 900, 950, 1050, 1200, 1000]
plt.figure(figsize=(12, 5))
# Első subplot
plt.subplot(1, 2, 1)  # 1 sor, 2 oszlop, 1. subplot
plt.plot(hónapok, bevételek, color='blue', marker='o')
plt.title('Havi bevételek')
plt.xlabel('Hónapok')
plt.ylabel('Bevétel (ezer Ft)')
# Második subplot
plt.subplot(1, 2, 2)  # 1 sor, 2 oszlop, 2. subplot
plt.plot(hónapok, kiadások, color='red', marker='D')
plt.title('Havi kiadások')
plt.xlabel('Hónapok')
plt.ylabel('Kiadások (ezer Ft)')
plt.tight_layout()  # Igazítás az optimális megjelenítéshez
plt.show()

print('Feladat: 4 diagram - másolással')
kiadások = [800, 900, 950, 870, 1000, 1200, 500, 900, 950, 1050, 1200, 1000]
plt.figure(figsize=(12, 5))
# Első subplot
plt.subplot(2, 2, 1)  # 1 sor, 2 oszlop, 1. subplot
plt.plot(hónapok, bevételek, color='blue', marker='o')
plt.title('Havi bevételek')
plt.xlabel('Hónapok')
plt.ylabel('Bevétel (ezer Ft)')
# Második subplot
plt.subplot(2, 2, 2)  # 1 sor, 2 oszlop, 2. subplot
plt.plot(hónapok, kiadások, color='red', marker='D')
plt.title('Havi kiadások')
plt.xlabel('Hónapok')
plt.ylabel('Kiadások (ezer Ft)')
# Harmadik subplot
plt.subplot(2, 2, 3)  # 1 sor, 2 oszlop, 1. subplot
plt.plot(hónapok, bevételek, color='blue', marker='o')
plt.title('Havi bevételek')
plt.xlabel('Hónapok')
plt.ylabel('Bevétel (ezer Ft)')
# Negyedik subplot
plt.subplot(2, 2, 4)  # 1 sor, 2 oszlop, 2. subplot
plt.plot(hónapok, kiadások, color='red', marker='D')
plt.title('Havi kiadások')
plt.xlabel('Hónapok')
plt.ylabel('Kiadások (ezer Ft)')
plt.tight_layout()  # Igazítás az optimális megjelenítéshez
plt.show()

print()

print('3. példa: scatterplot matplotlib-bel')
# Adatok generálása
latogatok = [120, 150, 180, 200, 170, 160, 140, 180, 190, 210, 190, 200]
eladasok = [150, 180, 220, 240, 200, 230, 170, 190, 210, 250, 230, 220]
# Scatter plot készítése
plt.scatter(latogatok, eladasok, color='red', marker='*') # s: marker mérete, alapértelmezett: s=20
plt.title('Látogatók száma és eladások közötti kapcsolat')
plt.xlabel('Látogatók száma')
plt.ylabel('Eladások száma')
plt.grid(True)
plt.show()

print()

print('4. példa: hisztogram matplotlib-bel')
# Adatok generálása
eladások = [150, 180, 220, 240, 200, 230, 170, 190, 210, 250, 230, 220]
# Hisztogram készítése
plt.hist(eladások, bins=6, color='lightblue', edgecolor='black')
plt.title('Napi eladások eloszlása')
plt.xlabel('Eladások száma')
plt.ylabel('Gyakoriság')
plt.grid(axis='y', linestyle='--', linewidth=0.5)
plt.show()

print()

print('5. példa: boxplot matplotlib-bel')
# Heti eladások adatai
heti_eladasok = [150, 180, 220, 240, 200, 230, 170, 190, 210, 250, 230, 220, 180, 195, 205]

# Box plot készítése
plt.boxplot(heti_eladasok,  patch_artist=True, boxprops=dict(facecolor='lightgreen'))   
plt.title('Heti eladások dobozábrája')
plt.ylabel('Eladások száma')
plt.grid(axis='y', linestyle='--', linewidth=0.5)
plt.show()

print()

print('Két boxplot egy diagramban')
# Heti eladások adatai
heti_eladasok_1 = [150, 180, 220, 240, 200, 230, 170, 190, 210, 250, 230, 220, 180, 195, 205]
heti_eladasok_2 = [160, 170, 210, 230, 190, 220, 180, 200, 220, 240, 210, 200, 170, 185, 195]
# Box plot készítése
fig, ax = plt.subplots()
# Első dobozábra
box1 = ax.boxplot(heti_eladasok_1, positions=[1], patch_artist=True, boxprops=dict(facecolor='lightgreen'))
# Második dobozábra
box2 = ax.boxplot(heti_eladasok_2, positions=[2], patch_artist=True, boxprops=dict(facecolor='lightblue'))
# Cím és tengelyfeliratok beállítása
plt.title('Heti eladások dobozábrája')
plt.ylabel('Eladások száma')
plt.xticks([1, 2], ['Heti eladások 1', 'Heti eladások 2'])  # x tengely címkék beállítása
# Rácsvonalak hozzáadása
plt.grid(axis='y', linestyle='--', linewidth=0.5)
# Diagram megjelenítése
plt.show()

print()

print('6. példa: overlay diagram matplotlib-bel')
# Adatok generálása
napok = ['Hétfő', 'Kedd', 'Szerda', 'Csütörtök', 'Péntek', 'Szombat', 'Vasárnap']
homerseklet = [20, 22, 21, 23, 24, 25, 22]  # Celsius fok
csapadek = [5, 10, 0, 0, 2, 1, 8]  # mm
napos_orak= [2, 2, 3, 4.5, 2.3, 2.5, 1.5]  # óra

plt.plot(napok, homerseklet, marker='o', color='red', label='Hőmérséklet (C°)')
plt.plot(napok, csapadek, marker='s', color='blue', linestyle='--', label='Csapadék (mm)')
plt.plot(napok, napos_orak, marker='*', color='orange', linestyle='-.', label='Napos órák (óra)')
plt.title('Heti időjárás: hőmérséklet, csapadék, napos órák száma')
plt.xlabel('Napok')
plt.ylabel('Érték')
plt.legend()
plt.grid(True)
plt.show()

print()

print('7. példa: egy diagramban két y tengelyes ábrázolás matplotlib-bel')
# Adatok generálása
honapok = ['Január', 'Február', 'Március', 'Április', 'Május', 'Június']
bevetelek = [1000, 1200, 1300, 1250, 1400, 1600]
latogatok = [800, 850, 900, 870, 950, 1000]

# Kombinált diagram készítése
fig, ax1 = plt.subplots()

# Első Y tengely: bevételek
ax1.plot(honapok, bevetelek, color='blue', marker='o', label='Bevétel')
ax1.set_xlabel('Hónapok')
ax1.set_ylabel('Bevétel (ezer Ft)', color='blue')
ax1.tick_params(axis='y', labelcolor='blue')

# Második Y tengely: látogatók száma
ax2 = ax1.twinx()
ax2.plot(honapok, latogatok, color='red', marker='s', linestyle='--', label='Látogatók száma')
ax2.set_ylabel('Látogatók száma', color='red')
ax2.tick_params(axis='y', labelcolor='red')

plt.title('Havi bevételek és látogatók száma')
fig.tight_layout()
plt.show()

print()

print('8. példa: vonaldiagram/oszlopdiagram Pandas-ban')
import pandas as pd
# CSV adatállomány betöltése
adatok = pd.read_csv('eladasok_30.csv')
adatok.plot(x='Nap', y='Eladás', kind='line', marker='x', color='green', title='Napi eladások')
plt.xlabel('Napok')
plt.ylabel('Eladások (db)')
plt.xticks(rotation=45)
plt.grid(True)
plt.show()
# A beolvasott adatok megtekintése
print(adatok.head())

print()

print('9. példa: Pandas hisztogram')
# Hisztogram készítése
adatok['Eladás'].plot(kind='hist', bins=6, color='skyblue', edgecolor='black', title='Eladások eloszlása')
plt.xlabel('Eladások száma')
plt.ylabel('Gyakoriság')
plt.show()

print()

print('10. példa: Seaborn pairplot')
import seaborn as sns
# Pairplot használata
sns.pairplot(adatok)
plt.suptitle('Páros diagram az eladások és látogatók számának vizsgálatához', y=0.98)
plt.show()

print()




