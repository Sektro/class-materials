import socket
import struct
import select

packerCommand = struct.Struct('3s 6s 6s f')
packerAnswer = struct.Struct('f')

serv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv_sock.bind(('localhost',10001))
serv_sock.listen(1)

sock_list = [serv_sock]

grades = []

while True:
    readable, _, _ = select.select(sock_list, [], [])
    for r in readable :
        if r == serv_sock :
            cli_sock, cli_addr = r.accept()
            print(cli_addr)
            sock_list.append(cli_sock)
        else :
            message = r.recv(1024)
            if message :
                command, subject, neptun, grade = packerCommand.unpack(message)
                subject = subject.decode()
                neptun = neptun.decode()
                command = command.decode()
                print(command)
                print(subject)
                print(neptun)
                print(grade)


                if command == "INS" :
                    found = False
                    for i in range(len(grades)) :
                        if grades[i]["neptun"] == neptun and grades[i]["subject"] == subject :
                            grades[i]["grade"] = grade
                            found = True
                    if not found :
                        grades.append({
                            "neptun": neptun,
                            "subject": subject,
                            "grade": grade
                        })
                    msg = packerAnswer.pack(grade)
                    r.send(msg)
                elif command == "GET" :
                    for g in grades :
                        if g["neptun"] == neptun and g["subject"] == subject :
                            msg = packerAnswer.pack(g["grade"])
                            r.send(msg)
                elif command == "AVG" :
                    sum = 0.0
                    count = 0.0
                    avg = 0.0
                    for g in grades :
                        if g["subject"] == subject :
                            sum += g["grade"]
                            count += 1
                    if count > 0 :
                        avg = sum / count
                    msg = packerAnswer.pack(avg)
                    r.send(msg)
            else :
                print("a user has disconnected")
                r.close()
                sock_list.remove(r)
serv_sock.close()