# 7. gyakorlat OOP II.
print('7. gyakorlat JSON fájlkezelés')

import json

print()    
print('12.dia: JSON fájl írása')
adatok = {
    "nev": "Anna",
    "kor": 25,
    "varos": "Budapest",
    "hobbik": ["olvasas", "sport", "zenehallgatas"],
    "kedvenc_szamok": {
        "1": "Queen - Bohemian Rhapsody",
        "2": "Led Zeppelin - Stairway to Heaven",
        "3": "Pink Floyd - Comfortably Numb"
    }
}
 # JSON fájl megnyitása írásra
with open("adatok.json", "w") as fajl:
    json.dump(adatok, fajl, indent=4)



print()
print('13.dia:JSON fájl olvasása')
print()
# JSON fájl megnyitása olvasásra
import json
with open("adatok.json", "r") as fajl:
    content = json.load(fajl)
    print(content)
    print(json.dumps(content, indent=4))