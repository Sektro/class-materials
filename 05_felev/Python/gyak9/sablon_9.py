
print('12.dia: checkpoint')
from os import strerror
try:
    with open('uj_text.txt', 'w')  as f:
        for i in range(10):
            f.write(str(i) + "x" + str(i+1) + " = " + str(i*(i+1)) + "\n")
except IOError as e:
    print("I/O error occurred: ", strerror(e.errno))

print()
from os import strerror
n = int(input('Kérek egy egész számot: '))
try:
    with open('uj_tabla.txt', 'w') as f:
        for i in range(11):
            f.write(str(i) + "x" + str(n) + " = " + str(i*n) + "\n")
except IOError as e:
    print("I/O error occurred: ", strerror(e.errno))

print('\n14.dia: csv fájlkezelés')
import csv
adatok = [
    ["Nev", "Kor", "Varos"],
    ["Anna", 25, "Budapest"],
    ["Bela", 30, "Szeged"],
    ["Cecilia", 28, "Debrecen"]    
]
with open("kimenet.csv", "w", newline='') as fajl:
    iro = csv.writer(fajl)
    iro.writerows(adatok)
    #csv.writer(fajl).writerows(adatok)


import csv
with open("kimenet.csv", "r") as fajl:
    olvaso = csv.reader(fajl)
    print(olvaso)
    for sor in olvaso:
        print(sor)


print('\n15.dia: Az OS modul')
import os, sys, platform
print(os.name)
print(platform.system())
print(os.getcwd())



print(sys.version)
print(sys.executable)
print(sys.path)


print('\n17.dia: Bevezetés a DATETIME modulba')
import datetime

from datetime import date
today = date.today()
print("Today:", today)
print("Year:", today.year)
print("Month:", today.month)
print("Day:", today.day)

d = date.fromisoformat('2025-11-12')
print(d)

d = date(2025,11,12)
print(d.weekday())   # hétfő=0, vasárnap=6

print('\nDátumobjektum létrehozása időbélyegből:')
import time
timestamp = time.time()
print("Timestamp:", timestamp)
d = date.fromtimestamp(timestamp)
print("Date:", d)

print('\ntime objektum létrehozása:')
from datetime import time
t = time(14, 53, 20, 1)
print("Time:", t)
print("Hour:", t.hour)
print("Minute:", t.minute)
print("Second:", t.second)
print("Microsecond:", t.microsecond)

print('\n18.dia: timestamp etódus')
from datetime import datetime
dt = datetime(2020, 10, 4, 14, 55)
print("Timestamp:", dt.timestamp())

print('A date és time formázása strftime metódussal:')
from datetime import time
from datetime import datetime
t = time(14, 53)
print(t.strftime("%H:%M:%S"))
dt = datetime(2020, 11, 4, 14, 53)
print(dt.strftime("%y/%B/%d %H:%M:%S"))

print('\nA naptár használata:')
import calendar
print(calendar.calendar(2024))
print(calendar.month(2020, 10))
calendar.prmonth(2024, 12)

calendar.setfirstweekday(calendar.SUNDAY)

print('\nTovábbi Datetime objektumok:')
import datetime
now = datetime.datetime.now()
print(now)
print(now.year, now.month, now.day)
print(now.hour, now.minute, now.second)
szuletesi_datum = datetime.datetime(2005, 3, 15)
print(szuletesi_datum)
eltelt_ido = now - szuletesi_datum
print(eltelt_ido.days, "nap telt el a születésed óta.")


print('\n19.dia: datetime.timedelta használata')
from datetime import datetime, timedelta

# két időpont (datetime objektum)
now = datetime.now()
szuletesi_datum = datetime(2005, 3, 15, 8, 30)  # 1990-05-15 08:30:00

# helyes mód: kivonás -> ez ad egy timedelta objektumot
eltelt = now - szuletesi_datum
print("Eltelt idő (alap):", eltelt)            # pl. "12976 days, 05:12:34.123456"
print("Összes nap:", eltelt.days)
print("Összes másodperc:", eltelt.total_seconds())

# bontás nap/óra/perc/másodperc formára
days = eltelt.days
secs_resten = eltelt.seconds   # napon belüli másodpercek (0..86399)
hours = secs_resten // 3600
minutes = (secs_resten % 3600) // 60
seconds = secs_resten % 60
print(f"{days} nap, {hours} óra, {minutes} perc, {seconds} másodperc")

# létrehozás konkrét értékekből
d1 = timedelta(days=7)                # egy hét
d2 = timedelta(hours=5, minutes=30)   # 5 óra 30 perc
print("Összegzett timedelta:", d1 + d2)  # 7 days, 5:30:00

# használat: hozzáadás/kivonás dátumhoz
jovo = now + timedelta(days=30)
mult_hete = now - timedelta(weeks=2)
print("30 nap múlva:", jovo)
print("2 héttel ezelőtt:", mult_hete)