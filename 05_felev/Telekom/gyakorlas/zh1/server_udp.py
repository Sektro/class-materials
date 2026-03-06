import struct
import socket

packer = struct.Struct('>20s i')

serv_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
serv_sock.bind(('localhost',11111))

while True :
  msg, addr = serv_sock.recvfrom(1024)
  print(addr)
  word, length = packer.unpack(msg)
  word = word.decode()
  new_word = ""
  for i in range(length) :
    new_word += word[i]
  new_word = new_word[::-1]
  serv_sock.sendto(new_word.encode(),addr)
serv_sock.close()