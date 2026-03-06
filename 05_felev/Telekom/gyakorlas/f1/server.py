import socket


serv_sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)

serv_sock.bind(('localhost',11111))
serv_sock.listen(1)

while True :
  cli_sock, cli_adr = serv_sock.accept()
  msg = cli_sock.recv(1024)
  print(msg.decode())
  cli_sock.send("Hello Kliens!".encode())
  cli_sock.close()
serv_sock.close()