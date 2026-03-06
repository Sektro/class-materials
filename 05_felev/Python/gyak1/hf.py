import random
import math

print("1.feladat")
a = []
for i in range(20):
    a.append(random.randint(1,99))
test = [20,20,11,12,11,11,1,1]
#a = test
print(a,"\n")


print("2.feladat")
print("összeg - ",sum(a),"\nátlag - ",sum(a)/len(a))
szorzat = 1
for i in a:
    szorzat = szorzat * i
print("szorzat - ",szorzat)
szorzatstring = str(szorzat)
print("szorzat hossza - ",len(szorzatstring),"\n")

print("3.feladat")
def maxmin(l) :
    max = l[0]
    maxind = 0
    min = l[0]
    minind = 0
    index = 0

    for i in l:
        if i > max:
            max = i 
            maxind = index
        if i < min:
            min = i
            minind = index
        index += 1

    maxminlist = [max,maxind,min,minind]
    return maxminlist

maxmin = maxmin(a)
print("maximum - ",maxmin[1] + 1,". helyen ",maxmin[0])
print("minimum - ",maxmin[3] + 1,". helyen ",maxmin[2])

print("4.feladat")
oszto = int(input("Adj meg egy osztót: "))
oszthatok = []

for i in a:
    if i % oszto == 0 :
        oszthatok.append(i)

print(oszthatok)

print("5.feladat")
def listmodus(l) :
    l.sort()
    nums = []
    previous = None

    for current in range(len(l)) :
        if previous != None and l[current] == l[previous] :
            nums.append(nums[current-1] + 1)
        else :
            nums.append(1)
        previous = current
    
    return l[nums.index(max(nums))]

print("Lista módusza:",listmodus(a))

def listmedian(l) :
    l.sort()

    if len(l) % 2 == 0 :
        medians = [l[math.floor(len(l)/2)-1],l[math.floor(len(l)/2)]]
        return medians
    else :
        return l[math.floor(len(l)/2)]

print("Lista mediánja(i):",listmedian(a))