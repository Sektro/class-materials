import time

def vowels_gen():
 for ch in "aeiou":
    yield ch
g = vowels_gen()
print(g)
print(next(g))
print(next(g)) 
print(list(vowels_gen())) # minden hívás új iterátort ad
for v in vowels_gen():
 print(v, end=' - ')
print()
print()

def időmérő_dekorátor(func):
    def wrapper(*args):
        before = time.time()
        result = func(*args)
        after = time.time()
        print(f"A függvény futási ideje: {after-before} másodperc")
        return result
    return wrapper

@időmérő_dekorátor
def counting(n):
    összeg = 0
    for i in range(n+1):
        összeg += i
    return összeg

print(counting(100))
print(counting(500000))
print(counting(1000000))