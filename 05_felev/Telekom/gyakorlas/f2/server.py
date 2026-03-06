import socket
import struct
import json

packer = struct.Struct('>100s 1i')

with open('users.json', 'r') as f:
  usernames = json.load(f)

serv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv_sock.bind(('localhost', 11111))
serv_sock.listen(1)

while True :
  cli_sock, cli_adr = serv_sock.accept()
  msg = cli_sock.recv(1030)
  msg, c_id = packer.unpack(msg)
  print('(' + str(c_id) + ',' + str(cli_adr) + '): \n' + msg.decode())
  cli_sock.send(f'Szia {usernames[str(c_id)]}!'.encode())
  cli_sock.close()
serv_sock.close()

  
