from os import strerror
try:
    s = open('prof.txt', "rt")
    content = s.read()
    l = content.split("\n")
    for i in range(len(l)) :
        l[i] = l[i].split(" ")
        l[i][2] = float(l[i][2])
    unique_names = []
    result = []
    for i in range(len(l)) :
        if l[i][0] not in unique_names :
            unique_names.append(l[i][0])
            result.append(l[i])
        else :
            for j in range(len(result)) :
                if result[j][0] == l[i][0] :
                    result[j][2] += l[i][2]
    s.close()
    print(result)
    with open('summary.txt', 'w') as f:
        for i in range(len(result)):
            f.write(result[i][0] + " " + result[i][1] + " " + str(result[i][2]) + "\n")
except IOError as e:
    print("I/O error occurred: ", strerror(e.errno))

# files: prof.txt, summary.txt