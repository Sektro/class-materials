import socket
import struct

CLIENT_ID = 4
packer = struct.Struct('>100s 1i')

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect(('localhost',11111))
msg = packer.pack('Szia Szerver!'.encode(),CLIENT_ID)
sock.send(msg)
msg = sock.recv(1024)
print(msg.decode())
sock.close()