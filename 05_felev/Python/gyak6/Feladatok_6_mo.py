# 1.feladat: Legyen a mondat string értéke: "Ki korán kel aranyat lel" 
# Írj kódot, amely a mondat szavait listába szervezi. Írasd ki a lista hosszát. 
#kód:
mondat = "Ki korán kel aranyat lel"
lista = mondat.split()
print(len(lista))


# 2.feladat: Döntsd el, hogy szerepel-e a "tél" szó a mondatban, 
# (függetlenül a kis és nagybetűktől). 
# Helyettesítsd a kódban a # karaktereket!
'''
def tél(mondat):
    return 'tél' in #.#().split()
print(tél("Tél van, hideg van."))
'''
def tél(mondat):
    return 'tél' in mondat.lower().split()  
print(tél("Tél van, hideg van."))

# 3.feladat: Írd meg a kódot, amely megszámolja a mondatban az "alma" szó előfordulásait 
# (függetlenül a kis és nagybetűktől). 
mondat = 'Az almafa ágán terem piros alma, zöld ALMA és sárga Alma is.'
#kód:
print(mondat.lower().count("alma"))

# 4.feladat: írj lambda függvényt, amelyik a ["alma", "birsalma", "körte", "banán", "barack"] listából 
# a "b" kezdőbetűjű elemek listáját adja vissza.
#kód:
lista = ["alma", "birsalma", "körte", "banán", "barack"]
b_kezd = lambda x: [i for i in x if i[0] == "b"]
print(b_kezd(lista))

# 5.feladat: írj lambda függvényt, amelyik a ["alma", "birsalma", "körte", "banán", "barack"] 
# lista szavainak nagybetűssé alakított szavait adja vissza listában.
#kód:
lista = ["alma", "birsalma", "körte", "banán", "barack"]
nagybetűs = lambda x: [i.upper() for i in x]
print(nagybetűs(lista))
nagyb = list(map(lambda y: y.upper(), lista))
print(nagyb)

# 6.feladat: írj lambda függvényt, amely a [0, 1, 2, 3, 4, 5, 6] listát
# megkapva a páros számokat adja vissza listában.
#kód:
lista = [0, 1, 2, 3, 4, 5, 6]
paros = lambda x: [i for i in x if i % 2 == 0]
print(paros(lista))
par = list(filter(lambda y: y % 2 == 0, lista))
print(par)

# 7.feladat: Írj lambda függvényt, amelyik a [1,2,3,4,5] listából a páratlan számok 
# négyzetét adja vissza! Használd a map() és a filter() függvényt!
#kód:
lista = [1,2,3,4,5]
ptl_négyzet = lambda x: list(map(lambda y: y**2, filter(lambda z: z % 2 != 0, x)))
print(ptl_négyzet(lista))
ptl_négyzet2 = list(map(lambda y: y**2, filter(lambda z: z % 2 != 0, lista)))
print(ptl_négyzet2)


# 8.feladat: Írj kódot, amely a ['alma', 'körte', 'szilva', 'szőlő', 'barack'] listából
# kiírja az 'l' betűt tartalmazó gyümölcsöket, valamint ezen gyümölcsök indexét egy-egy tuple-ben!
#kód:
r = []
lst = ['alma', 'körte', 'szilva', 'szőlő', 'barack']  
for i, elem in enumerate(lst):
    if 'l' in elem:
        pár = (i, elem)
        r.append(pár)
print(r)

# 9.feladat: Adott az alábbi dictionary: d = { "Adam" : 11, "Jerry" : 4, "Michael" : 45, "Ben" : 10} 
# Szűrd ki azokat a kulcsokat, amelyeknél az érték nagyobb, mint 10. Használj list comprehension-t!
d = { "Adam" : 11, "Ben" : 4, "Cecily" : 45, "David" : 10}
result = [k for k, v in d.items() if v > 10]
print(result)

# 10.feladat: Készíts egy Termék osztályt, amely tartalmazza a termék nevét és árát.
# Példányok: alma (300 Ft), körte (400 Ft), banán (250 Ft), narancs (350 Ft).
# Készíts egy Rendelés osztályt, amely termékek listáját tartalmazza.
# A Rendelés osztályban legyen egy metódus, amely kiszámolja a teljes rendelés árát.
# Készíts egy példányt a Rendelés osztályból, adj hozzá néhány Termék példányt. 
# Írasd ki a teljes rendelés termékeit és a teljes rendelés árát!
class Termék:
    def __init__(self, név, ár):
        self.név = név
        self.ár = ár
    def __str__(self):
        return f'Termék neve: {self.név}, ára: {self.ár} Ft'    
class Rendelés:
    def __init__(self):
        self.termékek = []
    def add_termék(self, termék):
        if not isinstance(termék, Termék):
            raise TypeError("Csak Termék típusú objektumok adhatók hozzá a rendeléshez!")
        self.termékek.append(termék)
    def teljes_ár(self):
        return sum(termék.ár for termék in self.termékek)
    def __str__(self):
        return "\n".join(str(termék) for termék in self.termékek)
rendelés = Rendelés()
termék1 = Termék("Alma", 300)
termék2 = Termék("Körte", 400)
termék3 = Termék("Banán", 250)
termék4 = Termék("Narancs", 350)

rendelés.add_termék(termék1)
rendelés.add_termék(termék3)
rendelés.add_termék(termék3)
print(rendelés)
print(f'A teljes rendelés ára: {rendelés.teljes_ár()} Ft')


