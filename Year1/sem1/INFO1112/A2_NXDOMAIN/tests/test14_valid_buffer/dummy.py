import socket
import time
def send_msg(msg):# pragma: no cover
    HOST = "127.0.0.1"  # The server's hostname or IP address
    PORT = 8888
    s= socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    s.connect((HOST, PORT))
    s.send(msg.encode())
    s.close()

def main(): # pragma: no cover
      # The port used by the server
    msg='google.com\n'
    msg2='goo'
    msg3='gle.com\n'
    msg4='!EXIT\n'
    HOST = "127.0.0.1"  # The server's hostname or IP address
    PORT = 8888
    send_msg(msg)
    s= socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    s.connect((HOST, PORT))
    s.send(msg2.encode())
    

    s.close()
    
    send_msg(msg2)
    send_msg(msg3)
    send_msg(msg4)
    
if __name__=='__main__': # pragma: no cover
    main()
