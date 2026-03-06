import socket
serv_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
cli_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

serv_sock.bind(('0.0.0.0',11111))

while True:
    data, addr = serv_sock.recvfrom(1024)
    print(data.decode()+str(addr))
    cli_sock.sendto(data,('localhost',12345))
    data, _ = cli_sock.recvfrom(1024)
    print("Válasz: " + data.decode())
    serv_sock.sendto(data,addr)
    
cli_sock.close()
serv_sock.close()