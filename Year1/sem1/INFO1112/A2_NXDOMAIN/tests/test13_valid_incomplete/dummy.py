import socket
import time
def send_msg(msg):# pragma: no cover
    HOST = "127.0.0.1"  # The server's hostname or IP address
    PORT = 8888
    s= socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    s.connect((HOST, PORT))
    s.send(msg.encode())
    data = s.recv(1024)
    data=data.decode()
    print(f"Received {data}",flush=True)
    s.close()

def main(): # pragma: no cover
      # The port used by the server
    msg='googl'
    msg2='e.com\n'
    msg3='!EX'
    msg4='IT\n'
    HOST = "127.0.0.1"  # The server's hostname or IP address
    PORT = 8888
    s= socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    s.connect((HOST, PORT))
    
    s.send(msg.encode())

    time.sleep(2)
    
    s.send(msg2.encode())
    # 
    s.close()

    s= socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    s.connect((HOST, PORT))
    
    s.send(msg3.encode())

    time.sleep(2)
    
    s.send(msg4.encode())
    # 
    s.close()
    
if __name__=='__main__': # pragma: no cover
    main()
