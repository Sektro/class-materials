print("1.Checkpoint")
print("1.Feladat")
mx = [ [1,2,3], [4,5,6], [7,8,9] ] 
print([x for row in mx for x in row])
print()

print("2.Feladat")
coords = [(x,y) for x in range(2) for y in range(3)]
print(coords)
print()

print("3.Feladat")
print("a)")
files = ["py.py", "py.py.txt", "hello.docx", "music.json", "names.txt", "doctor_x.xlsx", "abc.json"]
py_files = [f for f in files if f.endswith('.py')]
print(py_files)
print()

print("b)")
file_types = {f.split('.')[-1] for f in files} #működik, mivel halmazba generáltunk
print(file_types)
print()

print("c)")
db = {t: 0 for t in file_types}
for f in files:
    for t in file_types:
        if f.endswith('.' + t):
            db[t] += 1
print(db)
print()

db = {t: sum(1 for f in files if f.endswith('.' + t)) for t in file_types}
print(db)
print()

print("d)")
for t in file_types:
    print(t, end=': ')
    for f in files:
        if f.endswith('.' + t):
            print(f, end=', ')
    print()
print()


lista = [[1, 2], [3, 4]]
másolat = lista.copy()
másolat[0].append(3) # A beágyazott lista módosítása
print(lista) # [[1, 2, 3], [3, 4]] # változott!
print(másolat) # [[1, 2, 3], [3, 4]]
# listában lévő listákat nem másol át (csak a referenciája kerül bele) 

import copy
mély_másolat = copy.deepcopy(lista)
mély_másolat[0].append(3)
print(lista)
print(mély_másolat)
#deepcopy-val már működik
print()

def függvény(n):
 for i in range(n):
     yield i
# 1. Lista konverzió:
gen = függvény(3)
print(list(gen)) # [0, 1, 2]
# 2. Iterálás:
gen = függvény(3)
for érték in gen:
 print(érték) # 0, 1, 2
# 3. Next() használat:
gen = függvény(3)
print(next(gen)) # 0
print(next(gen)) # 1
print(next(gen)) # 2
# print(next(gen)) # StopIteration error
print()

def fibonacci(n):
 a = b = 1
 for i in range(n):
    if i == 0:
        yield 1
    else:
        a, b = b, a+b
        yield a
fibs = list(fibonacci(10))
print(fibs)

def háromszögszámok(n):
 s = 0
 for i in range(1,n+1):
    s += i
    yield s
for elem in háromszögszámok(10):
 print(elem)
print()

import random
def függvény(args, szabály):
    for x in args:
        print(szabály(x), end=' ')
    print()
lista = [random.randint(1, 100) for i in range(10)]
print(lista)
függvény(lista, lambda x: x % 3)
függvény(lista, lambda x: x % 5)
függvény(lista, lambda x: x + 1)
print()

print("2.Checkpoint")
print("1.Feladat")
import math
#print(math.pi)
#print(math.radians(180)/math.pi)
def fok_to_rad(n) -> float:
    return math.radians(n)
print(fok_to_rad(180))
print()

print("2.Feladat")
def Jaccard_index(set1 : set,set2 : set) -> float:
    intersect_size = len(set1 & set2)
    union_size = len(set1 | set2)
    return 0. if union_size == 0 else intersect_size / union_size
A = {1,2,3,4}
B = {1,2}
print(Jaccard_index(A,B))
print()

print("3.Feladat")
def quicksort(arr) :
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quicksort(left) + middle + quicksort(right)
print(quicksort([1,3,5,6,3,22,5,2,6,9,4,2]))
print()

