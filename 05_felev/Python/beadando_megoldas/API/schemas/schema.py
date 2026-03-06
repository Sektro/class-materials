from pydantic import BaseModel, PositiveInt, EmailStr
from typing import Union

'''

Útmutató a fájl használatához:

Az osztályokat a schema alapján ki kell dolgozni.

A schema.py az adatok küldésére és fogadására készített osztályokat tartalmazza.
Az osztályokban az adatok legyenek validálva.
 - az int adatok nem lehetnek negatívak.
 - az email mező csak e-mail formátumot fogadhat el.
 - Hiba esetén ValuErrort kell dobni, lehetőség szerint ezt a 
   kliens oldalon is jelezni kell.

'''

ShopName='Bolt'

class User(BaseModel):
    id: PositiveInt
    name: str
    email: EmailStr

class Item(BaseModel):
    item_id: PositiveInt
    name: str
    brand: str
    price: float
    quantity: PositiveInt

class Basket(BaseModel):
    id: PositiveInt
    user_id: PositiveInt
    items: list[Item]  

class MessageResponse(BaseModel):
    message: str

class FloatResponse(BaseModel):
    total:  float

SimpleResponse = Union[MessageResponse, FloatResponse]