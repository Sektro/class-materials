import socket
import struct
import random

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect(('localhost',11111))
packer = struct.Struct('>20s i')

with open("input.txt", "r") as f :
  content = f.read()
  f.close()

content = content.split("\n")
for i in range(len(content)) :
  content[i] = content[i].split(" ")
  content[i][1] = int(content[i][1])
print(content)


rnd = random.randint(1,3)
word = content[rnd-1][0] #input("please enter a word (max. 20 letters): ")
length = content[rnd-1][1] #int(input("please enter a number: "))
print(word)
print(length)
msg = packer.pack(word.encode(), length)
sock.send(msg)
new_word = sock.recv(20)
print(new_word)
sock.close()