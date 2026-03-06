import socket
import struct

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect( ('localhost',12345) )
while True :
    msg = input('Tranzakcio: ')
    sock.send(msg.encode())
    msg = sock.recv(1024)
    print(msg.decode())
sock.close()