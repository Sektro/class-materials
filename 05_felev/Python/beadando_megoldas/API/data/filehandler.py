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
        data = json.load(file)
        return data


def save_json(data: Dict[str, Any]) -> None:
    with open(JSON_FILE_PATH, "w", encoding="utf-8") as file:
        json.dump(data, file, indent=4, ensure_ascii=False)


def add_user(user: Dict[str, Any]) -> None:
    data = load_json()
    for users in data["Users"]:
        if users["id"] == user["id"]:
            raise ValueError(f"Felhasználó már létezik!")
    data["Users"].append(user)
    save_json(data)


def add_basket(basket: Dict[str, Any]) -> None:
    data = load_json()
    user_found = False
    for users in data["Users"]:
        if users["id"] == basket["user_id"]:
            user_found = True
            break
    if not user_found:
        raise ValueError(f"Felhasználó nem található!")
    for baskets in data["Baskets"]:
        if baskets["id"] == basket["id"]:
            raise ValueError(f"Kosár már létezik!")
    data["Baskets"].append(basket)
    save_json(data)


def add_item_to_basket(user_id: int, item: Dict[str, Any]) -> Dict[str, Any]:
    data = load_json()
    basket_found = False

    for basket in data["Baskets"]:
        if basket["user_id"] == user_id:
            basket_found = True
            for items in basket["items"]:
                if items["item_id"] == item["item_id"]:
                    items["quantity"] += item["quantity"]
                    save_json(data)
                    return basket
                
            basket["items"].append(item)
            save_json(data)
            return basket
    if not basket_found:
        raise ValueError(f"Felhasználó kosara nem található!")


def update_item_in_basket(user_id: int, item_id: int, updated_item: Dict[str, Any]) -> Dict[str, Any]:
    data = load_json()
    basket_found = False
    item_found = False

    for basket in data["Baskets"]:
        if basket["user_id"] == user_id:
            basket_found = True
            for item in basket["items"]:
                if item["item_id"] == item_id:
                    item_found = True
                    item.update(updated_item)
                    save_json(data)
                    return basket
                
    if not basket_found:
        raise ValueError(f"Felhasználó kosara nem található!")
    if not item_found:
        raise ValueError(f"Termék nem található a kosárban!")


def delete_item_from_basket(user_id: int, item_id: int) -> Dict[str, Any]:
    data = load_json()
    basket_found = False
    item_found = False
    for basket in data["Baskets"]:
        if basket["user_id"] == user_id:
            basket_found = True
            for item in basket["items"]:
                if item["item_id"] == item_id:
                    item_found = True
                    basket["items"].remove(item)
                    save_json(data)
                    return basket
    
    if not basket_found:
        raise ValueError(f"Felhasználó kosara nem található!")
    if not item_found:
        raise ValueError(f"Termék nem található a kosárban!")