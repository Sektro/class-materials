import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect(('localhost',11111))
sock.send("Hello Szerver!".encode())
msg = sock.recv(1024)
print(msg.decode())
sock.close()