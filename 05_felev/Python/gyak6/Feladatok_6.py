# 1.feladat: Legyen a mondat string értéke: "Ki korán kel aranyat lel" 
# Írj kódot, amely a mondat szavait listába szervezi. Írasd ki a lista hosszát. 
#kód:
print("1. feladat")
mondat1 = "Ki korán kel aranyat lel"
l1 = sorted(mondat1.split(' '))
print(l1)
print(f'lista hossza: {len(l1)}')
print()


# 2.feladat: Döntsd el, hogy szerepel-e a "tél" szó a mondatban, 
# (függetlenül a kis és nagybetűktől). 
# Helyettesítsd a kódban a # karaktereket!
'''
def tél(mondat):
    return 'tél' in #.#().split()
print(tél("Tél van, hideg van."))
'''
print("2. feladat")
def tél(mondat):
    return 'tél' in mondat.lower().split()
print(tél("Tél van, hideg van."))
print()

# 3.feladat: Írd meg a kódot, amely megszámolja a mondatban az "alma" szó előfordulásait 
# (függetlenül a kis és nagybetűktől). 
mondat = 'Az almafa ágán terem piros alma, zöld ALMA és sárga Alma is.'
#kód:
print('3. feladat')
print(f'almák száma: {len(mondat.lower().split("alma"))}') #mondat.lower().count("alma")
print()

# 4.feladat: írj lambda függvényt, amelyik a ["alma", "birsalma", "körte", "banán", "barack"] listából 
# a "b" kezdőbetűjű elemek listáját adja vissza.
#kód:
print("4. feladat")
blambda = lambda x: [y for y in x if y[0] == "b"]
print(blambda(["alma", "birsalma", "körte", "banán", "barack"]))
print()

# 5.feladat: írj lambda függvényt, amelyik a ["alma", "birsalma", "körte", "banán", "barack"] 
# lista szavainak nagybetűssé alakított szavait adja vissza listában.
#kód:
print("5. feladat")
nbetu = lambda x: [y.upper() for y in x]
print(nbetu(["alma", "birsalma", "körte", "banán", "barack"]))
print()

# 6.feladat: írj lambda függvényt, amely a [0, 1, 2, 3, 4, 5, 6] listát
# megkapva a páros számokat adja vissza listában.
#kód:
print("6. feladat")
paros = lambda x: [y for y in x if y % 2 == 0]
print(paros([0, 1, 2, 3, 4, 5, 6]))
print()

# 7.feladat: Írj lambda függvényt, amelyik a [1,2,3,4,5] listából a páratlan számok 
# négyzetét adja vissza. Használd a map() és a filter() függvényt!
#kód:
print("7. feladat")
mapfil = lambda x: list(map(lambda z: z * z,filter(lambda y: y % 2 == 1, x)))
print(mapfil([1,2,3,4,5]))
print()

# 8.feladat: Írj kódot, amely a ['alma', 'körte', 'szilva', 'szőlő', 'barack'] listából
# kiírja az 'l' betűt tartalmazó gyümölcsöket, valamint ezen gyümölcsök indexét egy-egy tuple-ben!
#kód:
print("8. feladat")
l2 = list(filter(lambda x: "l" in x,['alma', 'körte', 'szilva', 'szőlő', 'barack']))
for i in range(len(l2)) :
    tup = (l2[i], i)
    print(tup)
    print(type(tup))
print()

# 9.feladat: Adott az alábbi dictionary: d = { "Adam" : 11, "Jerry" : 4, "Michael" : 45, "Ben" : 10} 
# Szűrd ki azokat a kulcsokat, amelyeknél az érték nagyobb, mint 10. Használj list comprehension-t!
print("9. feladat")
d = { "Adam" : 11, "Jerry" : 4, "Michael" : 45, "Ben" : 10} 
print([key for key,value in d.items() if value > 10])
print()

# 10.feladat: Készíts egy Termék osztályt, amely tartalmazza a termék nevét és árát.
# Példányok: alma (300 Ft), körte (400 Ft), banán (250 Ft), narancs (350 Ft).
# Készíts egy Rendelés osztályt, amely termékek listáját tartalmazza.
# A Rendelés osztályban legyen egy metódus, amely kiszámolja a teljes rendelés árát.
# Készíts egy példányt a Rendelés osztályból, adj hozzá néhány Termék példányt. 
# Írasd ki a teljes rendelés termékeit és a teljes rendelés árát!
#kód:
print("10. feladat")
class Termék() :
    def __init__(self, név, ár) :
        self.__név  = név
        self.__ár = ár
    def getNév(self) :
        return self.__név
    def getÁr(self) :
        return self.__ár

alma, körte, banán, narancs = Termék("alma",300), Termék("körte",400), Termék("banán",250), Termék("narancs",350)
termékek = [alma, körte, banán, narancs]

class Rendelés() :
    def __init__(self) :
        self.__termék_lista = []
    def getLista(self) :
        return self.__termék_lista
    def add(self, termék) :
        self.__termék_lista.append(termék)
    def remove(self, termék) :
        self.__termék_lista.remove(termék)
    def allItems(self) :
        for t in self.__termék_lista :
            print(t.getNév())
        print(sum([y.getÁr() for y in self.__termék_lista]))
rendelés = Rendelés()
for t in termékek :
    rendelés.add(t)
rendelés.allItems()
print()