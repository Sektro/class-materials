import operator
import socket
import struct
import select


# ---- OPERATORS ---- 
ops = {
    '+' : operator.add,
    '-' : operator.sub,
    '*' : operator.mul,
    '/' : operator.truediv,  # use operator.div for Python 2
    '%' : operator.mod,
    '^' : operator.xor,
}

def eval_binary_expr(op1, oper, op2):
    op1, op2 = int(op1), int(op2)
    return ops[oper](op1, op2)

# examples:
# print(eval_binary_expr(*("1 + 3".split())))
# print(eval_binary_expr(*("1 * 3".split())))
# print(eval_binary_expr(*("1 % 3".split())))
# print(eval_binary_expr(*("1 ^ 3".split())))

# ---- SERVER SOCKET ---- 
serv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv_sock.bind(('localhost',11111))
serv_sock.listen(1)

sock_list = [serv_sock]

while True :
  readable, _, _ = select.select(sock_list, [], [])
  for r in readable :
    if r == serv_sock :
      cli_sock, cli_addr = serv_sock.accept()
      print(cli_addr)
      sock_list.append(cli_sock)
    else :
      msg = r.recv(1024)
      if msg :
        packer = struct.Struct('>1i 1s 1i')
        num1, op, num2 = packer.unpack(msg)
        result = eval_binary_expr(num1, str(op.decode()), num2)
        print("calculation complete!")
        packer = struct.Struct('>1i')
        result = packer.pack(result)
        r.send(result)
      else :
        print("client disconnected")
        r.close()
        sock_list.remove(r)
      
