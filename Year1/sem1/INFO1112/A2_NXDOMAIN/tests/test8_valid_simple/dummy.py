import socket
def send_msg(msg):# pragma: no cover
    HOST = "127.0.0.1"  # The server's hostname or IP address
    PORT = 8888
    s= socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    s.connect((HOST, PORT))
    s.send(msg.encode())
    data = s.recv(1024)
    data=data.decode()
    
    s.close()

def main(): # pragma: no cover
      # The port used by the server
    msg='www.google.com\n'
    msg2='!EXIT\n'
    send_msg(msg)
    send_msg(msg2)
if __name__=='__main__': # pragma: no cover
    main()
