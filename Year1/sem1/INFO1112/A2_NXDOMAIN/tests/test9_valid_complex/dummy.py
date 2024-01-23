import socket
def send_msg(msg):# pragma: no cover
    HOST = "127.0.0.1"  # The server's hostname or IP address
    PORT = 8886
    s= socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    s.connect((HOST, PORT))
    # print(f'{msg} sending to server',flush=True)
    s.send(msg.encode())
    # data = s.recv(1024)
    # data=data.decode()
    
    s.close()

def main(): # pragma: no cover
      # The port used by the server
    msg='google.com\n'
    msg2='com\n'
    msg3='!DEL com\n'
    msg4='!DEL info1112\n'
    msg5='com\n'
    msg6='!ADD www.github.com 1079\n'
    msg7='!ADD google.com 1057\n'
    msg8='google.com\n'
    msg9='www.github.com\n'
    msg10='!ADD google.com invalid\n'
    msg11='google.com\n'
    msg12='!ADD www..ms.com 1064\n'
    msg13='www..ms.com\n'
    msg14='\n'
    msg15='www/.ms.com\n'
    msg16='!EXIT\n'
    send_msg(msg)
    send_msg(msg2)
    send_msg(msg3)
    send_msg(msg4)
    send_msg(msg5)
    send_msg(msg6)
    send_msg(msg7)
    send_msg(msg8)
    send_msg(msg9)
    send_msg(msg10)
    send_msg(msg11)
    send_msg(msg12)
    send_msg(msg13)
    send_msg(msg14)
    send_msg(msg15)
    send_msg(msg16)
if __name__=='__main__': # pragma: no cover
    main()
