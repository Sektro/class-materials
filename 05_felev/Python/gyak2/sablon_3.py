print('15.dia példái\n')

print("abcabc".count("b"))
print('abcabc'.count("d"))
print('aBcD'.capitalize())
print('[' + 'labda'.center(10) + ']')
print('[' + 'labda'.center(10, "*") + ']')
if "almafa".endswith("fa"):
    print("igen")
if "almafa".startswith("al"):
    print("igen")
print("labda".find("da"))
print("labda".find("fa"))
print('labda'.find('a', 2))

print()
print("Anna".isalpha())
print('Anna20'.isalpha())
print('2024'.isdigit())
print("08:25".isdigit())
print('labda10'.isalnum())
print('#'.isalnum())
print('labda_10'.isalnum())
print(''.isalnum())
print("Anna".islower())
print("Bagoly".isupper())
print(' \n '.isspace())
print(" ".isspace())
print(" la la la".isspace())


print('\n16.dia példái\n')
print("kép kép kép".rfind("ké"))
print("kép kép kép".rfind("ké", 9))
print("kép kép kép".rfind("ké", 3, 10))

print()
print("alma körte\neper".split())
print("AdaT=60".lower())
print("[" + " alma ".lstrip() + "]")
print("elte.inf.hu".lstrip("elte."))
print("[" + " alma ".rstrip() + "]")
print("elte.inf.hu".rstrip(".hu"))
print("[" + " alma ".strip() + "]")
print("almalé".replace("lé", " lekvár"))
print("almalélé".replace("lé", " lekvár"))


print('\n17.dia példái\n')
print("Hej Dunáról fúj a szél".swapcase())
print("Hej Dunáról fúj a szél".title())
print("Hej Dunáról fúj a szél".upper())

print()
greek_1 = ['omega', 'alpha', 'pi', 'gamma']
greek_2 = sorted(greek_1)
print(greek_1)
print(greek_2)
greek_1.sort()
print(greek_1)
print(greek_2.sort(reverse = True))
print(greek_2)

print('\n19.dia checkpoint\n')
Tuple= (1, 2, 3)
print(Tuple[2])
tup = 1, 2, 3
print(tup)
a, b, c = tup
print(a * b * c)

print()
b = {2,4,6}
b.add(8)
print(b)
b.update([10,12,12])
print(b)
b.remove(6) ###
print(b)
b.discard(8) ###
print(b)
b.clear()
print(b) # set()

print()
dict_1= {"A": (1,5), "B": 2}
dict_2= dict_1.copy()
dict_1.clear()
print(dict_1)
print(dict_2)
del dict_1
#print(dict_1) # Error

print()
személy = {'name': 'Merlin', 'job': 'wizard'}
for kulcs, érték in személy.items():
    print(f'What is your {kulcs}? {érték}')