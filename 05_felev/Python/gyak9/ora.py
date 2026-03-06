#KVIZ

class Szamok:
    def __init__(self, szamok):
        self.szamok = szamok
    def __str__(self):
        return str(self.szamok)
    def __getitem__(self, index):
        return self.szamok[index]
    def __setitem__(self, index, value):
        self.szamok[index] = value
    
sz_1 = Szamok([1,2,3,4,5])
for i in sz_1:
    sz_1.__setitem__(i-1, i**2)
print(sz_1.__str__())


class Állat:
    def beszél(self):
        return "Az állat beszél."
class Kutya(Állat):
    def beszél(self):
        return f"{str(super().beszél())} A kutya ugat."
class Macska(Állat):
    def beszél(self):
        return f"{str(super().beszél())} A macska nyávog."
állatok = [Macska(), Kutya()]
print(állatok[1].beszél())
print()


#ORA

import errno
db = 0
for name, value in errno.errorcode.items():
 print(f"{name} → {value}")
 db += 1
print("Összesen: ", db, "db errno konstns van.")
print()

#import os, platform
#print(os.name())
#print(platform.system())

from datetime import datetime, timedelta
now = datetime.now()
szuletesi_datum = datetime(2005, 3, 15, 8, 30) # 2005-03-15 08:30:00
eltelt = now - szuletesi_datum # ez ad egy timedelta objektumot
print("Eltelt idő (alap):", eltelt) # pl. "12976 days, 05:12:34.123456"
print("Összes nap:", eltelt.days)
print("Összes másodperc:", eltelt.total_seconds())
days = eltelt.days # bontás nap/óra/perc/másodperc formára
secs_resten = eltelt.seconds # napon belüli másodpercek (0..86399)
hours = secs_resten // 3600
minutes = (secs_resten % 3600) // 60
seconds = secs_resten % 60
print(f"{days} nap, {hours} óra, {minutes} perc, {seconds} másodperc")
# létrehozás konkrét értékekből
d1 = timedelta(days=7) # egy hét
d2 = timedelta(hours=5, minutes=30) # 5 óra 30 perc
print("Összegzett timedelta:", d1 + d2) # 7 days, 5:30:00
jovo = now + timedelta(days=30) # hozzáadás/kivonás dátumhoz
mult_hete = now - timedelta(weeks=2)
print("30 nap múlva:", jovo)
print("2 héttel ezelőtt:", mult_hete)
print()

from flask import Flask
app = Flask(__name__)
@app.route('/')
def hello():
 return "Hello, Flask!"
if __name__ == "__main__":
 app.run(host="0.0.0.0", port=5000)
