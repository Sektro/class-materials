marks = { "Anna": [5,7.8], "Bea": [9.2,10], "Csaba": [7.8,9], "Dóra": [10,10], "Endre": [8,7.5], "Ferenc": [0,5]}

#for key,value in marks.items() :
#    print(f'{key}: {value}')

# a
for key,value in marks.items() :
    avg = (value[0] + value[1]) / 2
    print(f'{key}: Átlag: {avg}')

# b 
avgcsop = 0
for key,value in marks.items() :
    avgcsop += sum(value)
avgcsop /= len(marks) * 2
print(f'Osztályátlag: {avgcsop}')

# c
for key,value in marks.items() :
    avg = (value[0] + value[1]) / 2
    if avg >= 9 :
        print(f'{key}: Jegy: Jeles')
    elif avg >= 7.8 :
        print(f'{key}: Jegy: Jó')
    elif avg >= 6.4 :
        print(f'{key}: Jegy: Közepes')
    elif avg >= 5 :
        print(f'{key}: Jegy: Elégséges')
    else :
        print(f'{key}: Jegy: Elégtelen')

