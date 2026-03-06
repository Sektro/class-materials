import json
from typing import Dict, Any, List

'''
Útmutató a féjl használatához:

Felhasználó adatainak lekérdezése:

user_id = 1
user = get_user_by_id(user_id)
print(f"Felhasználó adatai: {user}")

Felhasználó kosarának tartalmának lekérdezése:

user_id = 1
basket = get_basket_by_user_id(user_id)
print(f"Felhasználó kosarának tartalma: {basket}")

Összes felhasználó lekérdezése:

users = get_all_users()
print(f"Összes felhasználó: {users}")

Felhasználó kosarában lévő termékek összárának lekérdezése:

user_id = 1
total_price = get_total_price_of_basket(user_id)
print(f"A felhasználó kosarának összára: {total_price}")

Hogyan futtasd?

Importáld a függvényeket a filereader.py modulból:

from filereader import (
    get_user_by_id,
    get_basket_by_user_id,
    get_all_users,
    get_total_price_of_basket
)

 - Hiba esetén ValuErrort kell dobni, lehetőség szerint ezt a 
   kliens oldalon is jelezni kell.

'''

# A JSON fájl elérési útja
JSON_FILE_PATH = "data/data.json"

def load_json() -> Dict[str, Any]:
    with open(JSON_FILE_PATH, "r", encoding="utf-8") as file:
        content = json.load(file)
        return content

def get_user_by_id(user_id: int) -> Dict[str, Any]:
    users = load_json()["Users"]
    for u in users :
        if u["id"] == user_id :
            return u
    raise ValueError(f"Ez a felhasználó nem létezik!")

def get_basket_by_user_id(user_id: int) -> List[Dict[str, Any]]:
    baskets = load_json()["Baskets"]
    for b in baskets :
        if b["user_id"] == user_id :
            return b["items"]
    raise ValueError(f"Ez a felhasználó nem létezik!")

def get_all_users() -> List[Dict[str, Any]]:
    users = load_json()["Users"]
    userlist = []
    for u in users :
        userlist.append(u)
    if userlist == [] :
        raise ValueError(f"Nem találhatóak felhasználók!")
    return userlist

def get_total_price_of_basket(user_id: int) -> float:
    sum = 0.0
    baskets = load_json()["Baskets"]
    for b in baskets :
        if b["user_id"] == user_id :
            for i in b["items"] :
                sum += i["price"]
            return sum
    raise ValueError(f"Ez a felhasználó nem létezik!")
