#try:
#    érték = int(input("Kérek egy számot: "))
#    print(1 / érték)
#except ValueError:
#    print("Helytelen bemenet.")
#except ZeroDivisionError:
#    print("Nagyon helytelen bemenet.")
#except:
#    print("Mi történt?") # Ctrl + C

személy = {'name': 'Merlin', 'job': 'wizard'}
for kulcs, érték in személy.items():
 print(f'What is your {kulcs}? {érték}')