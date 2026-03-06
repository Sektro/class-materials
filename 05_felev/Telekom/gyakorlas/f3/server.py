import socket
import struct
import select


serv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv_sock.bind(('localhost', 11111))
serv_sock.listen(1)

sock_list = [serv_sock]

while True :
  readable, _, _ = select.select(sock_list, [], [])
  for r in readable :
    if r == serv_sock :
      cli_sock, cli_addr = r.accept()
      print(cli_addr)
      sock_list.append(cli_sock)
    else :
      msg = r.recv(4)
      if msg :
        packer = struct.Struct("1i")
        strlen = packer.unpack(msg)[0]
        print(f"word length: {strlen}")
        msg = r.recv(1024)
        packer = struct.Struct(str(strlen)+'s 1i')
        word, multiplier = packer.unpack(msg)
        word = word.decode() * multiplier
        print("message sent to client")
        r.send(word.encode())
      else :
        print("a user has disconnected")
        r.close()
        sock_list.remove(r)
serv_sock.close()
