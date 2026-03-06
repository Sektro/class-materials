import socket
import struct

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect( ('localhost',12345) )
while True :
    # [sys.stdin, sock] --> selectnek átadva tudja majd, hogy kap/küld üzenetet
    msg = input()
    packer = struct.Struct('1i '+str(len(msg))+'s 1i')
    msg = packer.pack(len(msg),msg.encode(), 3)
    sock.send(msg)
    msg = sock.recv(1024)
    print(msg)
    print(msg.decode())
sock.close()

