print("1.feladat")

hour = int(input("Adjon meg egy órát: "))
minute = int(input("Adjon meg egy percet: "))
duration = int(input("Adjon meg egy időtartamot (percben): "))

minute += duration
hour += minute // 60
minute = minute % 60
hour = hour % 24

print(f'{hour}:{minute}')


print("2.feladat")

year = int(input("Kérem adjon meg egy évszámot: "))

if year < 1582 :
    print("Nem a Gergely-naptári időszakon belül van")
elif year % 4 != 0 :
    print("normál év")
elif year % 100 != 0 :
    print("szökő év")
elif year % 400 != 0 :
    print("normál év")
else :
    print("szökő év")


print("3.feladat")

n = int(input("Pozitiv egész szám: "))
while n != 1 :
    if n % 2 :
        n /= 2
    else :
        n = 3 * n + 1

print(n)

print("4.feladat")

magan = ['A','Á','E','É','I','Í','O','Ó','Ö','Ő','U','Ú','Ü','Ű']
szo = input("Adj meg egy szót: ").upper()
csak_mással = ""


for i in szo :
    if i not in magan :
        csak_mással = csak_mással + i
print(csak_mással)