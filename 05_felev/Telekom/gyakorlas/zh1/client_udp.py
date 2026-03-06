import socket
import struct
import random
import json

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
packer = struct.Struct('>20s i')

with open("input.json", "r") as f :
  content = json.load(f)
  f.close()

rnd = str(random.randint(1,3))
word = content[rnd][0]
length = content[rnd][1]
print(word)
print(length)
msg = packer.pack(word.encode(), length)
sock.sendto(msg,('localhost',11111))
new_word = sock.recvfrom(20)
print(new_word)
sock.close()