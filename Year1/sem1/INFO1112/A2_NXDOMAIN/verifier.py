"""
Write code for your verifier here.

You may import library modules allowed by the specs, as well as your own other modules.
"""
from sys import argv
from pathlib import Path
from urllib import robotparser


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


def is_valid_hostname_master(host):
    host = host.split('.')
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
    else:
        return False


def is_valid_master(master_content):
    i = 0
    if len(master_content) < 2:
        return False
    while i < len(master_content):
        if i == 0:
            sever_port = master_content[i].strip()
            port_check = is_valid_port(sever_port)
            if port_check == -1:
                return False
        if i != 0:
            content = master_content[i].strip().split(',')
            if len(content) != 2:
                return False

            host = content[0]
            h_valid = is_valid_hostname_master(host)
            port = content[1]
            port_check = is_valid_port(port)
            if port_check == -1:
                return False
            if h_valid == False:
                return False
            j = 1
            while j < len(master_content):
                content2 = master_content[j].strip().split(',')
                content2_host = content2[0]
                try:
                    content2_port = content2[1]
                except IndexError:
                    return False

                if host == content2_host and content2_port != port:
                    return False
                j += 1
        i += 1
    return True


def is_valid_hostname_single(host):

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


def is_valid_single(single_content):
    i = 0
    if len(single_content) < 2:
        return False
    while i < len(single_content):
        if i == 0:
            sever_port = single_content[i].strip()
            port_check = is_valid_port(sever_port)
            if port_check == -1:
                return False
        if i != 0:
            content = single_content[i].strip().split(',')
            if len(content) != 2:
                return False

            host = content[0]
            h_valid = is_valid_hostname_single(host)
            port = content[1]
            port_check = is_valid_port(port)
            if port_check == -1:
                return False
            if h_valid == False:
                return False
            j = 1
            while j < len(single_content):
                content2 = single_content[j].strip().split(',')
                content2_host = content2[0]
                try:
                    content2_port = content2[1]
                except IndexError:
                    return False

                if host == content2_host and content2_port != port:
                    return False
                j += 1
        i += 1
    return True


def is_valid_content(master, content):

    if len(master) != len(content):
        return False
    for i in content:
        if i.split(',')[0] not in master:
            return False
    return True


def verifying_intercon(root_content, tld_content, auth_content_port):
    for servers_root in root_content:
        port_dest_root = servers_root.split(',')[1]
        host_root = servers_root.split(',')[0]
        for servers_tld in tld_content:
            port_current_tld = servers_tld.split(',')[2]
            port_dest_tld = servers_tld.split(',')[1]
            host_tld = servers_tld.split(',')[0]
            # print(host_tld)
            host_tld_rt = host_tld.split('.')[1]
            if host_root == host_tld_rt and port_dest_root != port_current_tld:
                return False
            for servers_auth in auth_content_port:
                port_current_auth = servers_auth.split(',')[2]
                host_auth = servers_auth.split(',')[0]
                host_auth_tld = host_auth.split(
                    '.')[1]+',' + host_auth.split('.')[2]
                if host_auth_tld == host_tld and port_dest_tld != port_current_auth:
                    return False
    return True


def main(args: list[str]) -> None:
    # TODO
    # args=['/home/rubin/USYD/First_year/info1112/assignment2/nxdomain/master','/home/rubin/USYD/First_year/test']
    if len(args) != 2:
        print('invalid arguments', flush=True)
        exit()
    try:
        with open(args[0]) as master:
            master_content = master.readlines()
    except:
        print('invalid master', flush=True)
        exit()
    check_master = is_valid_master(master_content)
    if not check_master:
        print('invalid master', flush=True)
        exit()
    root_master = []
    tld_master = []
    auth_master = []
    i = 0
    for content in master_content:
        if i == 0:
            i = 1
            continue

        root_master.append(content.split(',')[0].split('.')[-1])
        tld_master.append(content.split(',')[0].split(
            '.')[-2]+'.'+content.split(',')[0].split('.')[-1])
        auth_master.append(content.strip())
    root_master = list(set(root_master))
    tld_master = list(set(tld_master))
    auth_master = list(set(auth_master))
    # print(auth_master)
    sever_port = []
    root_content = []
    tld_content = []
    auth_content_port = []
    auth_content = []
    file_loc = Path(args[1]).glob("**/*")
    file_to_scan = [x for x in file_loc if x.is_file()]  # list comprehension to get file under directory
    if file_to_scan == []:
        print('singles io error')
        exit()

    for files in file_to_scan:
        try:
            with open(files) as file_content:
                single_content = file_content.readlines()
                # print(single_content)
        except:
            print('singles io error')
            exit()

        check_single = is_valid_single(single_content)
        if not check_single:
            print("invalid single")
            exit()
        sever_port.append(single_content[0].strip())
        i = 1
        while i < len(single_content):
            len_of_content = len(
                single_content[i].strip().split(',')[0].split('.'))
            if len_of_content == 1:
                root_content.append(single_content[i].strip())
                root_content = list(set(root_content))
            elif len_of_content == 2:
                tld_content.append(
                    single_content[i].strip()+f',{single_content[0].strip()}')
                tld_content = list(set(tld_content))
            elif len_of_content >= 3:
                auth_content_port.append(
                    single_content[i].strip()+f',{single_content[0].strip()}')
                auth_content_port = list(set(auth_content_port))
                auth_content.append(single_content[i].strip())
            i += 1
    # print(root_content)
    # print(root_master)

    value1 = is_valid_content(root_master, root_content)
    value2 = is_valid_content(tld_master, tld_content)
    value3 = is_valid_content(auth_master, auth_content)
    value4 = False
    value5 = False

    check_indivial = value1+value2+value3
    sever_port_set = set(sever_port)
    if len(sever_port) == len(sever_port_set): #check port not the same
        value4 = True

    # print(tld_content)
    # print(root_content)
    # print(tld_content)
    # print(auth_content_port)
    if set(auth_content) == set(auth_master):
        value5 = True
    value6 = verifying_intercon(root_content, tld_content, auth_content_port)

    if check_indivial and value4 and value5 and value6:
        print('eq')
    else:
        print('neq')


if __name__ == "__main__":
    main(argv[1:])
