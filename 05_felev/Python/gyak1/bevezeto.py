print("1.feladat")
print("1-120 tömbben")

a = []

for i in range(120):
    a.append(i + 1)

print(a, "\n")

print("2. feladat")
print("tömb összeg")

print("sum-al: ",sum(a), "\n")

print("3.feladat")
print("3-mal osztható elemek")

b = []
for i in a:
    if i % 3 == 0 :
        b.append(i)

print(b, "\n")


print("4.feladat")
print("elemekek szorzata")

szorzat = 1
for i in a:
    szorzat = szorzat * i

print(szorzat,"\n")


print("5.feladat")
print("elemekek szorzatának hossza")

szorzatstring = str(szorzat)

print(len(szorzatstring))



print("6.feladat")
print("faktoriálisok 70-ig")

def fakt(n):
    if n <= 1 :
        return 1
    else:
        return fakt(n-1) * n

for i in range(70):
    print(i," - ",fakt(i))

print("7.feladat")
print("a tömbbe melyikek primek")

def prim(n):
    if n == 1:
        return False
    for i in range(n):
        if n % (i+1) == 0 and (i+1) != 1 and (i+1) != n:
            return False
    return True

for i in a:
    print(i," - ",prim(i))
