
print('8. gyakorlat')

print('\n2.dia: Iterátor és generátor példa: Vovels osztály')
class Vowels:
    def __init__(self):
        self.vow = "aeiou"
        self.pos = 0
    def __iter__(self):
        return self
    def __next__(self):
        if self.pos == len(self.vow): 
           raise StopIteration
        self.pos += 1        
        return self.vow[self.pos - 1]
vowels = Vowels()
for v in vowels:
    print(v, end=' - ')
print()
print(list(vowels))
print(list(Vowels()))

print('\nGenerator verzió:')
def vowels_gen():
    for ch in "aeiou":
        yield ch


print('\3.dia: Generátor használata')
g = vowels_gen()
print(next(g))   # 'a'
print(next(g))   # 'e'
print(list(vowels_gen()))
for v in vowels_gen():
    print(v, end=' - ')
print()


print('\n4.dia: 1.feladat: négyzetszám ellenőrzése')
def is_sqr(x):
    for i in range(round(x/2)):
        if i**2 == x:
            return True
    return False

print(is_sqr(24))       # False

is_sqr = lambda x: ( i**2==x for i in range(round(x/2)) )
print(is_sqr(24)) 
print(any(is_sqr(24)))    # False

is_sqr = lambda x:  any( i**2==x for i in range(round(x/2)) )
print(is_sqr(24))     # False
print(is_sqr(25))     # True


print('\n5.dia: Checkpoint: Auto osztály. 2.feladat: Kiegészítés Teherautó osztállyal')
class Auto:
    def __init__(self, marka, max_seb):
        self.marka = marka
        self.max_seb = max_seb
    def inform(self):
        return f"Márka: {self.marka}, Max sebesség: {self.max_seb} km/h"
class Szemelyauto(Auto):
    def inform(self):
        return f"Személyautó - {super().inform()}"
class AutoGyar:
    def gyart(tipus, marka, max_seb):
        if tipus == "személyautó":
            return Szemelyauto(marka, max_seb)
        else:
            return "Ismeretlen autótípus!"
car1 = AutoGyar.gyart("személyautó", "Toyota", 180)
car2 = AutoGyar.gyart("teherautó", "Volvo", 100)
print(car1.inform())
print(car2)


print('\n6.dia: 3.feladat: Szótár elemeinek vizsgálata')
car3 = AutoGyar.gyart("személyautó", "Opel", 170)
auto_dict = {
    "személyautó": [car1, car3], 
    "teherautó": car2,
}
print('Írd ki a szótár elemeit az inform() metódussal.')

max_speed = 0
max_car = None
for auto in auto_dict.values() :
    if isinstance(auto, list) :
        for a in auto :
            if  a.max_seb > max_speed :
                max_speed = a.max_seb
                max_car = a
    else :
        if  auto.max_seb > max_speed :
                max_speed = auto.max_seb
                max_car = auto
print(f"A legnagyobb sebességű autó: {max_car.inform()}")

for tip, auto in auto_dict.items():
    if isinstance(auto, list):
        for a in auto:
            print(f"{a.inform()}")
    else:
        print(f"{auto.inform()}")

print('\nMás módon: kulcsok bejárása')
for tip in auto_dict:
    auto = auto_dict[tip]
    if isinstance(auto, list):
        for a in auto:
            print(f"{a.inform()}")
    else:
        print(f"{auto.inform()}")

print('\nMás módon: értékek bejárása')
for auto in auto_dict.values():
    if isinstance(auto, list):
        for a in auto:
            print(f"{a.inform()}")
    else:
        print(f"{auto.inform()}")

print('\n3.feladat: írd ki a szótárban lévő járművek közül a legnagyobb sebességű jármű adatait.')


print('\n7.dia: Osztály dictionary példa')
class ExampleClass:            
    varia = 1              # Class variable
    def __init__(self, val): 
        self.varia = val   # Instance variable
        ExampleClass.varia = val
print(ExampleClass.__dict__)     # Class dictionary
ex_obj = ExampleClass(2) 
print(ex_obj.__dict__)           # Instance dictionary 
print(ExampleClass.__dict__)


print('\n9.dia: Dekorátor 1. példa')
def dekorátor(func):
    def wrapper(*args):
        print("Függvény meghívása előtt.")
        func(*args)
        print("Függvény meghívása után.")
    return wrapper

@dekorátor
def greeting(*names):
    for name in names:
        print(f"Szia {name}!")

greeting("Anna", "Béla", "Csaba")


print('\n10.dia: Dekorátor 2. példa')
def dekorátor(func):
    def wrapper(*args):
        print("Függvény meghívása előtt")
        result = func(*args)
        print("Függvény meghívása után")
        return result
    return wrapper

@dekorátor
def összeadás(*args):
    return sum(args)

print(f"Eredmény: {összeadás(1, 3, 5)}")


print('\n10.dia: Dekorátor 3. példa')
def nagybetus_dekor(func):
    def wrapper(*args):
        result = func(*args)
        return result.upper()
    return wrapper

@nagybetus_dekor
def greet(name):
    return f"Hello {name}!"

print(greet("Anna"))  


print('\n10.dia: 4.példa Paraméteres dekorátor ')
def ismétlő_dekor(num):
    def dekorátor(func):
        def wrapper(*a):
            result = None
            for _ in range(num):
                result = func(*a)
            return result
        return wrapper
    return dekorátor

@ismétlő_dekor(4)
def greet(name):
    print(f"Hello {name}!")

greet("Anna")


print('\n16.dia: Dekorátor 4. példa')
print('4.feladat: Készíts a counting függvény futási idejének mérésére szolgáló dekorátort.')

import time

def időmérő_dekorátor(func):
    pass

@időmérő_dekorátor
def counting(n):
    összeg = 0
    for i in range(n+1):
        összeg += i
    return összeg

print(counting(100))


