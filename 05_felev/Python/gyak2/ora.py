# PRINT

print("\n|-------PRINT-------|\n")
print("I'm", "learning", "Python", sep = "---", end = "****")
print()
print("I'm", "learning", "Python", sep = "\n")
print('22'+'333')
print(4*('22'+'333'))
print('\"I\'m\"')
print("+"+ 10*"-"+"+")
print(("|"+ " "*10 + "|\n")*5,end="")
print("+" + 10 * "-"+"+")
print()
print ("%s széles: %d, hosszú: %d, magas: %f" % ('Téglatest adatai:',12 ,35, 14.6))
a, b, c = 12, 35, 14.6
print ("Széles: %d, hosszú: %d, magas: %f" % (a, b, c))
print("Széles: {}, hosszú: {}, magas: {}".format(a, b, c) )
print("A téglatest térfogata =", a*b*c, "egység")
print("cím: {}/{}, idő: {}, tartam: {:.2f}".format("Delta", 12, '16:45', 46.278) )
márka = "Opel"
típus = "Astra"
print(f"Az {típus} egy {márka} gyártmány.")

# ADATTIPUSOK
# Elemi: int(), float(), complex(), bool()
# Összetett: str(), tuple(), list(), dict(), set()

print("\n|-------ADATTIPUSOK-------|\n")
print(bool(False))  #False
print(bool('False'))#True
print(bool(0))      #False
print(bool('0'))    #True
print(bool(''))     #False



print(0.000000000002)
print(0o123)            # 8-as számrendszer (octadecimális)
print(0x123)            # 16-as számrendszer (hexadecimális)


# ÉRTÉKADÁS

print("\n|-------ÉRTÉKADÁS-------|\n")
alma, körte, dió = 26, 15, 4
alma = körte = dió = 15

# BITENKÉNTI LOGIKAI MŰVELETEK
# ~, &, |, ^, >>, <<

# A B ~B A&B A|B A^B
# 1 1 0   1   1   0
# 1 0 1   0   1   1
# 0 1 0   0   1   1
# 0 0 1   0   0   0

x = 4           #x = 0000 0100
y = 1           #y = 0000 0001
a = x & y       #a = 0000 0000
b = x | y       #b = 0000 0101
c = ~x          #c = 1111 1011
d = x ^ 5       #d = 0000 0001
e = x >> 2      #e = 0000 0001
f = x << 2      #f = 0001 0000
print(a, b, c, d, e, f)

# x + -x = csupa 0 2-es számrendszerben

# bitenkénti eltolás
x >> 1 # Osztunk 2-vel
x << 2 # Szorzunk 4-gyel

print(bin(7))
print(0b011)
