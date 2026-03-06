import socket
import sys
import struct

packerAsk = struct.Struct("6s 10s i")
packerRecieve = struct.Struct("10s i")
packerAffirm = struct.Struct("6s 10s i")
packerAnswer = struct.Struct("12s")

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((str(sys.argv[1]),int(sys.argv[2])))

neptun = "bhyuha"
messageCounter = 0
spaceNeeded = 21
scheduledDate = "2030-07-07"
found = False

while not found :
    messageCounter += 1
    msg = packerAsk.pack(neptun.encode(),scheduledDate.encode(),messageCounter)
    sock.send(msg)
    msg = sock.recv(1024)
    class_id, capacity = packerRecieve.unpack(msg)
    if capacity >= spaceNeeded :
        found = True
        messageCounter += 1
        msg = packerAffirm.pack(neptun.encode(),class_id,messageCounter)
        sock.send(msg)
        msg = sock.recv(1024)
        code = packerAnswer.unpack(msg)[0]
print(code.decode())
sock.close()

