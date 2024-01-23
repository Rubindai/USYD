"""
Write code for your launcher here.

You may import library modules allowed by the specs, as well as your own other modules.
"""
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
            h_valid = is_valid_hostname(host)
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


def available_port(log_port, assign_port):
    assign_port = int(assign_port)
    j = 0
    while j < len(log_port):
        if assign_port == int(log_port[j]):
            assign_port += 1
            if assign_port != int(log_port[j])+1:
                assign_port = str(assign_port)
                log_port.append(assign_port)
                log_port.sort()
                return assign_port

            else:
                j = -1
        j += 1
    assign_port = str(assign_port)
    log_port.append(assign_port)
    log_port.sort()
    return assign_port


def write_file(path, content):
    try:
        files = open(path, 'w')
    except:
        print("NON-WRITABLE SINGLE DIR")
        exit()
    files.writelines(content)
    files.close()


def main(args: list[str]) -> None:
    # TODO
    # args=['/home/rubin/USYD/First_year/info1112/assignment2/nxdomain/master','/home/rubin/USYD/First_year/info1112/assignment2/nxdomain']
    if len(args) != 2:
        print('INVALID ARGUMENTS', flush=True)
        exit()

    try:
        with open(args[0]) as master:
            master_content = master.readlines()
    except:
        print('INVALID MASTER', flush=True)
        exit()
    check_master = is_valid_master(master_content)
    if not check_master:
        print('INVALID MASTER', flush=True)
        exit()
    log_port = []
    root_port = master_content[0].strip()
    file_loc = args[1]
    i = 1
    root_file = [root_port+'\n']

    auth_file = []
    root_content = []
    tld_content = []
    log_port.append(root_port)
    while i < len(master_content):
        content = master_content[i].strip().split(',')
        host = content[0]
        port = content[1]
        host = host.split('.')
        section_a = host[-1]
        section_b = host[-2]
        section_c_list = host[:-2]
        section_c = ''
        for j in section_c_list:
            section_c += j+'.'
        section_c = section_c[:-1]
        root_content.append(section_a)
        tld_content.append(section_b+'.'+section_a)
        i += 1
        log_port.append(port)
    log_port.sort()
    assign_port = 1024
    tld_content = list(set(tld_content))
    root_content = list(set(root_content))

    i = 0
    tld_port = []
    while i < len(root_content):
        assign_port = available_port(log_port, assign_port)
        tld_port.append(assign_port)
        root_temp = root_content[i]+','+assign_port
        if i != len(root_content)-1:
            root_temp += '\n'
        root_file.append(root_temp)
        i += 1

    write_file(file_loc+'/root.conf', root_file)

    i = 0
    assign_port = available_port(log_port, assign_port)  # auth port

    while i < len(root_content):

        tld_file = [tld_port[i]+'\n']
        j = 0
        while j < len(tld_content):
            hostname = tld_content[j].split('.')[1]
            if hostname == root_content[i]:

                tld_temp = tld_content[j]+','+assign_port
                if j != len(tld_content)-1:
                    tld_temp += '\n'
                tld_file.append(tld_temp)

            j += 1

        write_file(file_loc+f'/{root_content[i]}'+'_tld.conf', tld_file)
        i += 1

    auth_file = [assign_port+'\n']
    i = 1
    while i < len(master_content):
        auth_file.append(master_content[i])
        i += 1
    write_file(file_loc+'/auth.conf', auth_file)


if __name__ == "__main__":
    main(argv[1:])
