#packer = struct.Struct('1i') #i i 4s - 4 db karakter, l - 1 db long, 3i - 3 db int
import socket
import struct
import select

serv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv_sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
serv_sock.bind( ('0.0.0.0',12345) ) #this address is already in use (ez a sor mondja)

serv_sock.listen(1)
sock_list = [serv_sock]
while True:
    readable, _, _ = select.select(sock_list, [], []) #visszaadja azokat a listából amik olvashatóak
    #akik küldtek valamit, de még nem foglalkoztunk vele
    #blokkoló utasitás
    for r in readable :
        if r == serv_sock :
            cli_sock, cli_addr = r.accept()
            print(cli_addr)
            sock_list.append(cli_sock)
        else :
            msg = r.recv(4)
            if msg :
                # rendes üzenetet kaptunk
                print(msg)
                packer = struct.Struct('1i')
                string_len = packer.unpack(msg)[0]
                print(string_len)
                msg = r.recv(1024)
                print(msg)
                packer = struct.Struct(str(string_len)+'s 1i')
                
                msg, multi = packer.unpack(msg)
                print(msg.decode() + " - " + str(multi))
                answ = ""
                for i in range(multi):
                    answ = answ + msg.decode()
                r.send(answ.encode())
            else :
                # kliens bontotta a kapcsolatot
                print("Kilepett egy user")
                r.close()
                sock_list.remove(r)
serv_sock.close()

# Ez az alapstruktúrája a szervernek, innentől kezdve az összes logikája ugyanigy fog működni

# ZH: elméleti kviz (gyakorlati anyag) pl.: mit csinál a struct fgv és milyen paramétereket lehet neki adni
# vagy connect fgv mivel tér vissza TCP esetében (Gyakorlás: diák+canvas oldalak)
# kód: részfeladatok  p.:valósitsunk meg egy olyan üzenetküldést ami 4 karaktert tartalmaz... 
# szerver megirasa (akkor valahova fel kell majd tölteni a kódot)
# UDP (majd köv órán)

#ZH gyakorlásnak: számológépes feladat (+ több klienses megoldás) --> 6.gyakorlat dia