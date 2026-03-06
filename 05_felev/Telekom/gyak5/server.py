import socket
import json
import struct
import sys

packer = struct.Struct('>12000s 1i')

usernames = {}
with open('usernames.json', 'r') as f:
    usernames = json.load(f)

serv_sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
#serv_sock.bind( ('localhost',12345) )
serv_sock.bind( ('localhost',int(sys.argv[1])) )
serv_sock.listen(1) #1 kliens lehet aki a szerver acceptjére vár

while True:
    cli_sock, cli_addr = serv_sock.accept()
    print(cli_addr)
    msg = cli_sock.recv(1024000)
    print(msg)
    msg, c_id = packer.unpack(msg)
    print(msg.decode() + " - " + str(c_id))
    cli_sock.send(('Szia kliens ' + usernames[str(c_id)]).encode()) #encode: python 3 csak bytestringeket kűldözget
    cli_sock.close()
serv_sock.close()

#beragadt terminál kinyirására Ctrl + Pause ( = Break)