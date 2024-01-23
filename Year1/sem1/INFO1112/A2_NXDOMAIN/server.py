"""
Write code for your server here.

You may import library modules allowed by the specs, as well as your own other modules.
"""

import socket
from sys import argv


def is_valid_port(port):
    try:
        port = int(port)

    except:

        port = -1

    if port < 1024 or port > 65535:
        port = -1
    return port


def valid_section_a_b(section_a):
    valid_char1 = (
        'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-')
    for i in section_a:

        if i not in valid_char1:
            return False
    return True


def valid_section_c(section_c):
    if section_c[0] == '.' or section_c[-1] == '.':
        return False
    valid_char2 = (
        'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.')
    for i in section_c:

        if i not in valid_char2:
            return False
    return True


def is_valid_hostname(host):

    host = host.split('.')  # host is list here
    if len(host) == 1:

        section_a = host[0]
        value = valid_section_a_b(section_a)
        return value
    if len(host) == 2:
        section_a = host[0]
        section_b = host[1]
        value_a = valid_section_a_b(section_a)
        value_b = valid_section_a_b(section_b)
        value = value_a and value_b
        return value
    if len(host) >= 3:
        section_a = host[-1]
        section_b = host[-2]
        section_c_list = host[:-2]
        section_c = ''
        for i in section_c_list:
            section_c += i+'.'
        section_c = section_c[:-1]
        if section_a == '' or section_b == '' or section_c == '':
            return False

        value_a = valid_section_a_b(section_a)
        value_b = valid_section_a_b(section_b)
        value_c = valid_section_c(section_c)
        value = value_a and value_b and value_c
        return value


def is_valid_config(config_content):
    i = 0
    if len(config_content) < 2:
        return False
    while i < len(config_content):
        if i == 0:
            sever_port = config_content[i].strip()
            port_check = is_valid_port(sever_port)
            if port_check == -1:
                return False
        if i != 0:
            content = config_content[i].strip().split(',')
            if len(content) != 2:
                return False

            host = content[0]
            h_valid = is_valid_hostname(host)
            port = content[1]
            port_check = is_valid_port(port)
            if port_check == -1:
                return False
            if h_valid == False:
                return False
            j = 1
            while j < len(config_content):
                content2 = config_content[j].strip().split(',')
                content2_host = content2[0]
                try:
                    content2_port = content2[1]
                except IndexError:
                    return False

                if host == content2_host and content2_port != port:  # same domain different port
                    return False
                j += 1
        i += 1
    return True


def main(args: list[str]) -> None:
    # TODO
    # args=['/home/rubin/USYD/First_year/info1112/assignment2/nxdomain/config_file']
    if len(args) != 1:
        print('INVALID ARGUMENTS', flush=True)
        exit()

    try:
        with open(args[0]) as config:
            config_content = config.readlines()
    except:
        print('INVALID CONFIGURATION', flush=True)
        exit()
    check_config = is_valid_config(config_content)
    if not check_config:
        print('INVALID CONFIGURATION', flush=True)
        exit()

    sever_port = is_valid_port(config_content[0].replace('\n', ''))

    HOST = '127.0.0.1'

    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind((HOST, sever_port))
    server.listen()

    with server:
        while True:
            conn, addr = server.accept()

            with conn:
                buffer = ''
                while True:

                    flag_valid = 0

                    data = conn.recv(1024).decode()
                    if not data:
                            break # start listening to new conn when client disconnect

                    buffer += data
                    loc = buffer.find('\n')
                    if loc == -1:
                        continue
                    data = buffer[0:loc]

                    buffer = buffer[loc+1:]
                    try:
                        data[0]
                    except:
                        print('INVALID')
                        continue
                    if data[0] == '!':

                        if data == '!EXIT':

                            flag_valid = 1
                            conn.close()
                            server.close()
                            exit()
                        if data[0:4] == '!DEL':
                            flag_valid = 1
                            i = 1
                            while i < len(config_content):
                                hostname = config_content[i].strip().split(',')
                                if data[5:].strip() == hostname[0]:
                                    del config_content[i]
                                i += 1

                        if data[0:4] == '!ADD':
                            flag_overwrite = 0
                            flag_valid = 1
                            data = data.split(' ')
                            content_h = data[1]
                            content_p = data[2]
                            content_f = content_h+','+content_p
                            config_replace = config_content.copy()
                            i = 1

                            while i < len(config_replace):
                                hostname = config_replace[i].strip().split(',')
                                if content_h.strip() == hostname[0]:
                                    flag_overwrite = 1
                                    config_replace[i] = content_f+'\n'
                                    check_re = is_valid_config(config_replace)
                                    if check_re:
                                        config_content[i] = content_f+'\n'
                                i += 1
                            if flag_overwrite == 0:
                                config_replace.append(content_f)
                                check_re = is_valid_config(config_replace)
                                if check_re:
                                    config_content.append(content_f)

                    else:
                        value = is_valid_hostname(data)
                        if value:
                            flag_valid = 1
                            i = 1
                            to_send = ''
                            while i < len(config_content):

                                hostname = config_content[i].strip().split(',')
                                if data == hostname[0]:
                                    to_send = hostname[1].strip()
                                    to_send += '\n'
                                i += 1
                            if to_send == '':
                                to_send = 'NXDOMAIN\n'
                            print(
                                f"resolve {data} to {to_send.strip()}", flush=True)
                            to_send = to_send.encode()
                            conn.send(to_send)
                    if flag_valid == 0:
                        print("INVALID")


if __name__ == "__main__":
    main(argv[1:])
