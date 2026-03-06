from schemas.schema import User, Basket, Item, SimpleResponse
from fastapi.responses import JSONResponse, RedirectResponse
from fastapi import FastAPI, HTTPException, Request, Response, Cookie
from fastapi import APIRouter
from data.filehandler import (
    add_user,
    add_basket,
    add_item_to_basket,
    update_item_in_basket,
    delete_item_from_basket,
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
    try:
        add_user(user.model_dump())
        return JSONResponse(content=user.model_dump(), status_code=201)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))


@routers.post('/addshoppingbag', response_model=SimpleResponse)
def addshoppingbag(userid: int) -> str:
    try:
        add_basket({
            "id": 100+userid,
            "user_id": userid,
            "items": []
        })
        return JSONResponse(content={"message": "Sikeres kosár hozzárendelés."}, status_code=201)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))


@routers.post('/additem', response_model=Basket)
def additem(userid: int, item: Item) -> Basket:
    try:
        basket = add_item_to_basket(userid, item.model_dump())
        return JSONResponse(content=basket, status_code=201)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))


@routers.put('/updateitem', response_model=Basket)
def updateitem(userid: int, itemid: int, updateItem: Item) -> Basket:
    try:
        basket = update_item_in_basket(userid, itemid, updateItem.model_dump())
        return JSONResponse(content=basket, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))


@routers.delete('/deleteitem', response_model=Basket)
def deleteitem(userid: int, itemid: int) -> Basket:
    try:
        basket = delete_item_from_basket(userid, itemid)
        return JSONResponse(content=basket, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))


@routers.get('/user', response_model=User)
def user(userid: int) -> User:
    try:
        user = get_user_by_id(userid)
        return JSONResponse(content=user, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=404, detail=str(e))


@routers.get('/users', response_model=list[User])
def users() -> list[User]:
    try:
        users = get_all_users()
        return JSONResponse(content=users, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=404, detail=str(e))


@routers.get('/shoppingbag', response_model=list[Item])
def shoppingbag(userid: int) -> list[Item]:
    try:
        basket = get_basket_by_user_id(userid)
        return JSONResponse(content=basket, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=404, detail=str(e))


@routers.get('/getusertotal', response_model=SimpleResponse)
def getusertotal(userid: int) -> float:
    try:
        total = get_total_price_of_basket(userid)
        return JSONResponse(content={"total": total}, status_code=200)
    except ValueError as e:
        raise HTTPException(status_code=404, detail=str(e))
