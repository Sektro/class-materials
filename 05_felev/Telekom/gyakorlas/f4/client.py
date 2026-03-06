import socket
import struct


sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect(('localhost', 11111))

while True :
  num1 = input("please enter a number: ")
  num2 = input("please enter a second number: ")
  num1 = int(num1)
  num2 = int(num2)
  op = input("please enter an operator: ")
  packer = struct.Struct('>1i 1s 1i')
  msg = packer.pack(num1,op.encode(),num2)
  sock.send(msg)
  packer = struct.Struct('>1i')
  msg = packer.unpack(sock.recv(1024))[0]
  print(msg)
sock.close()