from pydantic import BaseModel
from typing import Annotated 
from pydantic import EmailStr, Field

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

ShopName='Webshop'

class User(BaseModel):
    id: Annotated[int,  (description="felhasználó azonosítója", ge=0)]
    name: Annotated[str, Field(description="felhasználó neve")]
    email: Annotated[EmailStr, Field(description="felhasználó emailje")]

class Item(BaseModel):
    item_id: Annotated[int, Field(description="termék azonosítója", ge=0)]
    name: Annotated[str, Field(description="termék neve")]
    brand: Annotated[str, Field(description="termék márkája")]
    price: Annotated[float, Field(description="termék ára", gt=0)]
    quantity: Annotated[int, Field(description="termék mennyisége", ge=0)]


class Basket(BaseModel):
    id: Annotated[int, Field(description="kosár azonosítója", ge=0)]
    user_id: Annotated[int, Field(description="kosár tulajdonos azonosítója", ge=0)]
    items: Annotated[list[Item], Field(description="kosárban lévő termékek")]

