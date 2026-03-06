#---------------1.FELADAT---------------#
print("1.feladat")

time1 = input("Kérem az első időpontot (óra perc másodperc): ").split(" ")
for t in range(len(time1)) :
    time1[t] = int(time1[t])
time2 = input("Kérem a második időpontot (óra perc másodperc): ").split(" ")
for t in range(len(time2)) :
    time2[t] = int(time2[t])

time1sec = time1[0] * 3600 + time1[1] * 60 + time1[2]
time2sec = time2[0] * 3600 + time2[1] * 60 + time2[2]

differenceSec = abs(time1sec - time2sec)

time3sec = 0
if time1sec > time2sec :
    time3sec = time1sec + differenceSec
else :
    time3sec = time2sec + differenceSec

differenceHour = differenceSec // 3600
differenceSec -= differenceHour * 3600
differenceMin = differenceSec // 60
differenceSec -= differenceMin * 60

print(f'Az időpontok közötti különbség: {differenceHour}:{differenceMin}:{differenceSec}')

time3 = []
time3.append(time3sec // 3600)
time3sec -= (time3sec // 3600) * 3600
time3.append(time3sec // 60)
time3sec -= (time3sec // 60) * 60
time3.append(time3sec)

print(f'Új időpont: {time3[0]}:{time3[1]}:{time3[2]}')


#---------------2.FELADAT---------------#
print("2.feladat")

cimletek = [20000,10000,5000,2000,1000,500,200,100,50,20,10,5][::-1]
osszeg = int(input("Kérem az összeget (Ft): "))

while osszeg != 0 :
    cimlet = cimletek.pop()
    db = osszeg // cimlet
    osszeg = osszeg % cimlet
    if db != 0 :
        print(f'{db} db 20000 Ft = {db * cimlet}')

print("Címletezve:")
#while (osszeg > 0) :
#    db = 0
#    if osszeg // 20000 > 0 :
#        db = osszeg // 20000
#        print(f'{db} db 20000 Ft = {db * 20000}')
#        osszeg -= db * 20000
#        cimletek.pop(cimletek.index(20000))
#    elif osszeg // 10000 > 0 :
#        db = osszeg // 10000
#        print(f'{db} db 10000 Ft = {db * 10000}')
#        osszeg -= db * 10000
#        cimletek.pop(cimletek.index(10000))
#    elif osszeg // 5000 > 0 :
#        db = osszeg // 5000
#        print(f'{db} db 5000 Ft = {db * 5000}')
#        osszeg -= db * 5000
#        cimletek.pop(cimletek.index(5000))
#    elif osszeg // 2000 > 0 :
#        db = osszeg // 2000
#        print(f'{db} db 2000 Ft = {db * 2000}')
#        osszeg -= db * 2000
#        cimletek.pop(cimletek.index(2000))
#    elif osszeg // 1000 > 0 :
#        db = osszeg // 1000
#        print(f'{db} db 1000 Ft = {db * 1000}')
#        osszeg -= db * 1000
#        cimletek.pop(cimletek.index(1000))
#    elif osszeg // 500 > 0 :
#        db = osszeg // 500
#        print(f'{db} db 500 Ft = {db * 500}')
#        osszeg -= db * 500
#        cimletek.pop(cimletek.index(500))
#    elif osszeg // 200 > 0 :
#        db = osszeg // 200
#        print(f'{db} db 200 Ft = {db * 200}')
#        osszeg -= db * 200
#        cimletek.pop(cimletek.index(200))
#    elif osszeg // 100 > 0 :
#        db = osszeg // 100
#        print(f'{db} db 100 Ft = {db * 100}')
#        osszeg -= db * 100
#        cimletek.pop(cimletek.index(100))
#    elif osszeg // 50 > 0 :
#        db = osszeg // 50
#        print(f'{db} db 50 Ft = {db * 50}')
#        osszeg -= db * 50
#        cimletek.pop(cimletek.index(50))
#    elif osszeg // 20 > 0 :
#        db = osszeg // 20
#        print(f'{db} db 20 Ft = {db * 20}')
#        osszeg -= db * 20
#        cimletek.pop(cimletek.index(20))
#    elif osszeg // 10 > 0 :
#        db = osszeg // 10
#        print(f'{db} db 10 Ft = {db * 10}')
#        osszeg -= db * 10
#        cimletek.pop(cimletek.index(10))
#    elif osszeg // 5 > 0 :
#        db = osszeg // 5
#        print(f'{db} db 5 Ft = {db * 5}')
#        osszeg -= db * 5
#        cimletek.pop(cimletek.index(5))
#
#
#print(cimletek)


#---------------3.FELADAT---------------#
print("3.feladat")

magan = ['A','Á','E','É','I','Í','O','Ó','Ö','Ő','U','Ú','Ü','Ű','Y']
massal = ['B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Z']
abc = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
szo = input("Kérem adj meg egy szót: ").upper()

# 1
maganszo = []
massalszo = []
for i in range(len(szo)) :
    if szo[i] in magan :
        maganszo.append(szo[i])
    elif szo[i] in massal :
        massalszo.append(szo[i])
print(maganszo)
print(massalszo)

# 2
masikszo = szo
for i in range(len(szo)-1, -1, -1) :
    masikszo += szo[i]
print(masikszo)

# masikszo = szo + szo[::-1]

# 3
szoabcben = ""
maradekszo = []
for i in range(len(szo)) :
    maradekszo.append(szo[i])

for i in range(len(szo)) :
    betu = maradekszo[0]
    for j in range(len(maradekszo)) :
        if abc.index(maradekszo[j]) < abc.index(betu) :
            betu = maradekszo[j]
    szoabcben += betu
    maradekszo.pop(maradekszo.index(betu))

print(szoabcben)

# 4
titkositott = ""
titkositottlista = []
for i in range(len(szo)) :
    titkositottlista.append(szo[i])


for i in range(len(titkositottlista)) :
    if abc.index(titkositottlista[i]) + 1 < len(abc) :
        titkositottlista[i] = abc[abc.index(titkositottlista[i]) + 1]

        # titkositottlista[i] = chr(ord(titkositottlista[i]) + 1)

    else :
        titkositottlista[i] = abc[0]
    titkositott += titkositottlista[i]
print(titkositott)