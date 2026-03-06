from schemas.schema import User, Basket, Item
from fastapi.responses import JSONResponse, RedirectResponse
from fastapi import FastAPI, HTTPException, Request, Response, Cookie
from fastapi import APIRouter
from data.filehandler import (
    add_user,
    add_basket,
    add_item_to_basket,
    delete_item,
    update_item
)
from data.filereader import (
    get_user_by_id,
    get_basket_by_user_id,
    get_all_users,
    get_total_price_of_basket
)
'''

Útmutató a fájl használatához:

- Minden route esetén adjuk meg a response_modell értékét (típus)
- Ügyeljünk a típusok megadására
- A függvények visszatérési értéke JSONResponse() legyen
- Minden függvény tartalmazzon hibakezelést, hiba esetén dobjon egy HTTPException-t
- Az adatokat a data.json fájlba kell menteni.
- A HTTP válaszok minden esetben tartalmazzák a 
  megfelelő Státus Code-ot, pl 404 - Not found, vagy 200 - OK

'''

routers = APIRouter()

@routers.post('/adduser', response_model=User)
def adduser(user: User) -> User:
    """ 
    Felhasználó hozzáadására használt függvény.

    Paraméterek:\n
            -user: hozzáadandó felhasználó\n
    """
    try:
        add_user(user.model_dump())
        return JSONResponse(content=user.model_dump(), status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e))

@routers.post('/addshoppingbag')
def addshoppingbag(userid: int) -> str:
    """ 
    Kosár hozzáadására használt függvény.

    Paraméterek:\n
            -userid: felhasználó id-ja, akihez a kosarat rendeljük\n
    """
    try:
        add_basket({
            "id": userid+69,
            "user_id": userid,
            "items": []
        })
        return JSONResponse(content={"message": "Kosár hozzáadva."}, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e))

@routers.post('/additem', response_model=Basket)
def additem(userid: int, item: Item) -> Basket:
    """ 
    Termék hozzáadására használt függvény.

    Paraméterek:\n
            -userid: felhasználó, akinek a kosarába a terméket tesszük\n
            -item: a hozzáadandó termék\n
    """
    try:
        basket = add_item_to_basket(userid, item.model_dump())
        return JSONResponse(content=basket, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e))

@routers.put('/updateitem')
def updateitem(userid: int, itemid: int, updateItem: Item) -> Basket:
    """ 
    Termék frissítésére használt függvény.

    Paraméterek:\n
            -userid: felhasználó, akinek a kosarát módosítjuk\n
            -itemid: frissített termék id-je\n
            -updateItem: friss termék\n
    """
    try:
        basket = update_item(userid, itemid, updateItem.model_dump())
        return JSONResponse(content=basket, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e))

@routers.delete('/deleteitem')
def deleteitem(userid: int, itemid: int) -> Basket:
    """ 
    Termék törlésére használt függvény.

    Paraméterek:\n
            -userid: felhasználó, akinek a kosarát módosítjuk\n
            -itemid: törölt termék id-je\n
    """
    try:
        basket = delete_item(userid, itemid)
        return JSONResponse(content=basket, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e))

@routers.get('/user')
def user(userid: int) -> User:
    """ 
    Felhasználó lekérésére használt függvény.

    Paraméterek:\n
            -userid: lekért felhasználó id-je\n
    """
    try:
        user = get_user_by_id(userid)
        return JSONResponse(content=user, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e))

@routers.get('/users')
def users() -> list[User]:
    """ 
    Összes felhasználó lekérésére használt függvény.
    """
    try:
        users = get_all_users()
        return JSONResponse(content=users, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e))

@routers.get('/shoppingbag')
def shoppingbag(userid: int) -> list[Item]:
    """ 
    Kosár lekérésére használt függvény.

    Paraméterek:\n
            -userid: lekért kosár tulajdonosának id-je\n
    """
    try:
        basket = get_basket_by_user_id(userid)
        return JSONResponse(content=basket, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e))

@routers.get('/getusertotal')
def getusertotal(userid: int) -> float:
    """ 
    Felhasználó kosarának összértékének lekérésére használt függvény.

    Paraméterek:\n
            -userid: lekért kosár tulajdonosának id-je\n
    """
    try:
        total = get_total_price_of_basket(userid)
        return JSONResponse(content={"total": total}, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=422, detail=str(e))



