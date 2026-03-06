import socket

serv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
cli_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

serv_sock.bind(('localhost',11111))
serv_sock.listen(1)

while True :
  cli_sock2, cli_addr = serv_sock.accept()
  msg = cli_sock2.recv(1024)
  print(msg.decode(),cli_addr)
  cli_sock.sendto(msg,('localhost',12345))
  data, _ = cli_sock.recvfrom(1024)
  print(data.decode())
  cli_sock2.send(data)
serv_sock.close()
cli_sock.close()