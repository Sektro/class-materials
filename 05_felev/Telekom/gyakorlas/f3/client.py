import socket
import struct 


sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect(('localhost',11111))

while True :
  msg = input("Please enter a word: ")
  multi = input("Please enter a multiplier: ")
  print(len(msg))
  packer = struct.Struct("1i" + str(len(msg)) + "s 1i")
  msg = packer.pack(len(msg),msg.encode(),int(multi))
  sock.send(msg)
  msg = sock.recv(1030)
  print(msg.decode())
sock.close()
