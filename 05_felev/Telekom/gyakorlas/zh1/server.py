import struct
import socket
import select

packer = struct.Struct('>20s i')

serv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv_sock.bind(('localhost',11111))
serv_sock.listen(1)

sock_list = [serv_sock]

while True :
  readable, _, _ = select.select(sock_list, [], []) #visszaadja azokat a listából amik olvashatóak
  for r in readable :
    if r == serv_sock :
      cli_sock, cli_addr = serv_sock.accept()
      print(cli_addr)
      sock_list.append(cli_sock)
    else :
      msg = r.recv(1024)      
      if msg :
        word, length = packer.unpack(msg)
        word = word.decode()
        new_word = ""
        for i in range(length) :
          new_word += word[i]
        new_word = new_word[::-1]
        r.send(new_word.encode())
      else :
        print("client disconnected")
        r.close()
        sock_list.remove(r)