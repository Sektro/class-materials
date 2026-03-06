#packer = struct.Struct('1i') #i i 4s - 4 db karakter, l - 1 db long, 3i - 3 db int
import socket
import struct
import select

serv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv_sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
serv_sock.bind( ('0.0.0.0',12345) ) #this address is already in use (ez a sor mondja)

serv_sock.listen(1)
sock_list = [serv_sock]
balances = {}

while True:
    readable, _, _ = select.select(sock_list, [], []) #visszaadja azokat a listából amik olvashatóak
    #akik küldtek valamit, de még nem foglalkoztunk vele
    #blokkoló utasitás
    for r in readable :
        if r == serv_sock :
            cli_sock, cli_addr = r.accept()
            print(cli_addr)
            sock_list.append(cli_sock)
            balances[cli_sock] = 10000
        else :
            msg = r.recv(1024)
            if msg :
                balances[r] += int(msg.decode())
                r.send(('Uj egyenleg: '+str(balances[r])).encode())
            else :
                # kliens bontotta a kapcsolatot
                print("Kilepett egy user")
                r.close()
                sock_list.remove(r)
                del(balances[r])
serv_sock.close()
