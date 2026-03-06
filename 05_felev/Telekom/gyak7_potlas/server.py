import socket
serv_sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
serv_sock.bind(('localhost',12345))

while True:
    data, addr = serv_sock.recvfrom(1024)
    print(data.decode()+str(addr))
    serv_sock.sendto("OK".encode(),addr)