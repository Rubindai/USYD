"""
Write code for your recursor here.

You may import library modules allowed by the specs, as well as your own other modules.
"""
from sys import argv
import socket
import time


def is_valid(host):
    valid_char1 = (
        'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-')
    valid_char2 = (
        'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.')
    host = host.split('.')
    if len(host) <= 2:
        print('INVALID', flush=True)
        section_a = None
        section_b = None
        section_c = None
        return section_a, section_b, section_c

    if len(host) >= 3:
        section_a = host[-1]
        section_b = host[-2]
        section_c_list = host[:-2]
        section_c = ''
        for i in section_c_list:
            section_c += i+'.'
        section_c = section_c[:-1]

        if section_a == '' or section_b == '' or section_c == '':
            print('INVALID', flush=True)
            section_a = None
            section_b = None
            section_c = None
            return section_a, section_b, section_c
        else:
            for i in section_a:
                k = 0
                for j in valid_char1:
                    if i == j:
                        k += 1
                        break
                if k == 0:
                    print('INVALID', flush=True)
                    section_a = None
                    section_b = None
                    section_c = None
                    return section_a, section_b, section_c
            for i in section_b:
                k = 0
                for j in valid_char1:
                    if i == j:
                        k += 1
                        break
                if k == 0:
                    print('INVALID', flush=True)
                    section_a = None
                    section_b = None
                    section_c = None
                    return section_a, section_b, section_c
            if section_c[0] == '.' or section_c[-1] == '.':
                print('INVALID', flush=True)
                section_a = None
                section_b = None
                section_c = None
                return section_a, section_b, section_c
            else:
                for i in section_c:
                    k = 0
                    for j in valid_char2:
                        if i == j:
                            k += 1
                            break
                    if k == 0:
                        print('INVALID', flush=True)
                        section_a = None
                        section_b = None
                        section_c = None
                        return section_a, section_b, section_c
            return section_a, section_b, section_c


def server_res(host, port, flag, section=None, time=0):
    format = 'utf-8'

    if flag == 1:
        try:
            server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            server.connect((host, port))
        except:

            print("FAILED TO CONNECT TO ROOT", flush=True)
            exit()

        server.close()

    if flag >= 2:
        try:

            server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

            server.connect((host, port))
        except:

            if flag == 3:
                print('FAILED TO CONNECT TO TLD', flush=True)
                exit()
            if flag == 4:
                print('FAILED TO CONNECT TO AUTH', flush=True)
                exit()

        section_msg = section.encode(format)
        try:
            server.sendall(section_msg)
            server.settimeout(time)
            port = server.recv(1024).decode(format)
        except:
            server.close()
            port = 'NXDOMAIN\n'
            return port

        try:
            port = int(port)
        except:
            port = port
        server.close()
        return port


def is_valid_port(port):
    try:
        port = int(port)

    except:

        port = -1

    if port < 1024 or port > 65535:
        port = -1
    return port


def main(args: list[str]) -> None:
    # TODO
    if len(args) != 2:
        print('INVALID ARGUMENTS', flush=True)
        exit()

    format = 'utf-8'
    HOST = "127.0.0.1"
    flag1 = 1
    try:
        time = float(args[1])
    except:
        print('INVALID ARGUMENTS', flush=True)
        exit()

    port_root = is_valid_port(args[0])
    if port_root == -1:
        print('INVALID ARGUMENTS', flush=True)
        exit()
    server_res(HOST, port_root, flag1)

    while True:
        try:

            flag1 = 2

            host = input()

            sections_a, sections_b, sections_c = is_valid(host)
            if sections_a == None or sections_b == None or sections_c == None:
                continue

            sections_a += '\n'
            port_tld = server_res(HOST, port_root, flag1, sections_a, time)

            if port_tld == 'NXDOMAIN\n':
                print('NXDOMAIN', flush=True)

                continue

            flag1 += 1

            port_auth = server_res(HOST, port_tld, flag1,
                                   sections_b+'.'+sections_a, time)

            if port_auth == 'NXDOMAIN\n':
                print('NXDOMAIN', flush=True)

                continue

            flag1 += 1
            dns_port = server_res(HOST, port_auth, flag1,
                                  sections_c+'.'+sections_b+'.'+sections_a, time)

            if dns_port == 'NXDOMAIN\n':
                print('NXDOMAIN', flush=True)

                continue
            else:
                print(dns_port, flush=True)

        except EOFError:
            exit()

if __name__ == "__main__":
    main(argv[1:])
