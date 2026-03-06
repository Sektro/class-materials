import socket
import struct

packerClient = struct.Struct("i 20s")

serv_sock1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv_sock2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
cli_sock1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
cli_sock2 = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

serv_sock1.bind(('localhost',10003))
serv_sock1.listen(1)
serv_sock2.bind(('localhost',10003))
serv_sock2.listen(1)
cli_sock1.bind(('localhost',10001))
cli_sock1.listen(1)
cli_sock2.bind(('localhost',10002))

