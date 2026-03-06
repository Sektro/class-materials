############################################################
### You do not have to modify or understand this script. ###
### It is only used for testing. Please ignore it.       ###
############################################################

import socket
import select

LISTEN_PORT = 12345

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_sock:
    server_sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_sock.bind(('', LISTEN_PORT))
    server_sock.listen()
    print(f"Listening on TCP port {LISTEN_PORT}")
    inputs = [server_sock]
    while True:
        readable, _, _ = select.select(inputs, [], [])
        for sock in readable:
            if sock is server_sock:
                new_sock, (ip, _) = server_sock.accept()
                inputs.append(new_sock)
                new_sock.sendall(ip.encode())
                print(f'New connection: {new_sock.getpeername()}')
            else:
                data = sock.recv(4096)
                if data:
                    print(f"Received from {sock.getpeername()}: {data}")
                else:
                    print(f"Disconnected: {sock.getpeername()}")
                    inputs.remove(sock)
                    sock.close()
