import json
from typing import Dict, Any


'''
Útmutató a fájl függvényeinek a használatához

Új felhasználó hozzáadása:

new_user = {
    "id": 4,  # Egyedi felhasználó azonosító
    "name": "Szilvás Szabolcs",
    "email": "szabolcs@plumworld.com"
}

Felhasználó hozzáadása a JSON fájlhoz:

add_user(new_user)

Hozzáadunk egy új kosarat egy meglévő felhasználóhoz:

new_basket = {
    "id": 104,  # Egyedi kosár azonosító
    "user_id": 2,  # Az a felhasználó, akihez a kosár tartozik
    "items": []  # Kezdetben üres kosár
}

add_basket(new_basket)

Új termék hozzáadása egy felhasználó kosarához:

user_id = 2
new_item = {
    "item_id": 205,
    "name": "Szilva",
    "brand": "Stanley",
    "price": 7.99,
    "quantity": 3
}

Termék hozzáadása a kosárhoz:

add_item_to_basket(user_id, new_item)

Hogyan használd a fájlt?

Importáld a függvényeket a filehandler.py modulból:

from filehandler import (
    add_user,
    add_basket,
    add_item_to_basket,
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

def save_json(data: Dict[str, Any]) -> None:
    with open(JSON_FILE_PATH, "w", encoding="utf-8") as file:
        json.dump(data, file, indent=2, ensure_ascii=False)

def add_user(user: Dict[str, Any]) -> None:
    data = load_json()
    for u in data["Users"]:
        if u["id"] == user["id"]:
            raise ValueError(f"Ez a felhasználó már létezik!")
    data["Users"].append(user)
    save_json(data)

def add_basket(basket: Dict[str, Any]) -> None:
    data = load_json()
    found = False
    for b in data["Baskets"]:
        if b["user_id"] == basket["user_id"]:
            raise ValueError(f"Ennek a felhasználónak már van kosara!")
    for u in data["Users"]:
        if u["id"] == basket["user_id"]:
            data["Baskets"].append(basket)
            found = True
    if found == False :
        raise ValueError(f"Ez a felhasználó nem létezik!")
    save_json(data)

def add_item_to_basket(user_id: int, item: Dict[str, Any]) -> None:
    data = load_json()
    found_basket = False

    for b in data["Baskets"]:
        if b["user_id"] == user_id:
            found_basket = True
            for i in b["items"] :
                if i["item_id"] == item["item_id"] :
                    i["quantity"] += item["quantity"]
                    save_json(data)
                    return b
            b["items"].append(item)
            save_json(data)
            return b

    if not found_basket :
        raise ValueError(f"Ez a felhasználó nem létezik!")

def delete_item(user_id: int, item_id: int) -> Dict[str, Any]:
    data = load_json()
    found_basket = False
    found_item = False
    for b in data["Baskets"] :
        if b["user_id"] == user_id :
            found_basket = True
            for i in b["items"] :
                if i["item_id"] == item_id :
                    item_found = True
                    b["items"].remove(i)
                    save_json(data)
                    return b

    if not found_basket :
        raise ValueError(f"Ez a felhasználó nem létezik!")
    if not found_item :
        raise ValueError(f"Ez a termék nem létezik!")


def update_item(user_id: int, item_id: int, updated_item: Dict[str, Any]) -> Dict[str, Any]:
    data = load_json()
    found_basket = False
    found_item = False
    for b in data["Baskets"] :
        if b["user_id"] == user_id :
            found_basket = True
            for i in b["items"] :
                if i["item_id"] == item_id :
                    item_found = True
                    i.update(updated_item)
                    save_json(data)
                    return b

    if not found_basket :
        raise ValueError(f"Ez a felhasználó nem létezik!")
    if not found_item :
        raise ValueError(f"Ez a termék nem létezik!")



