import socket
cli_sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)

cli_sock.sendto(input("Üzenet: ").encode(),('localhost',11111))
data, addr = cli_sock.recvfrom(1024)
print(data.decode() + str(addr))
cli_sock.close()