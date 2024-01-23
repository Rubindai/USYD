'''
Interface of the exam
'''

import setup
from exam import Exam

import sys


def parse_cmd_args(args):
    '''
    Parameters:
        args: list, command line arguments
    Returns:
        result: None|tuple, details of the exam

    >>> parse_cmd_args(['program.py', '/home/info1110/', '60', '-r'])
    ('/home/info1110/', 60, True)

    >>> parse_cmd_args(['program.py', '/home/info1110/', 'ab', '-r'])
    Duration must be an integer

    >>> parse_cmd_args(['program.py', '/home/info1110/'])
    Check command line arguments
    '''

    if len(args) < 3:
        print("Check command line arguments")
        return None
    try:
        duration = int(args[2])
    except:
        print("Duration must be an integer")
        return None
    try:
        if args[3] == '-r':
            shuffle = True
    except:
        shuffle = False
    path = args[1]
    return path, duration, shuffle


def setup_exam(obj):
    '''
    Update exam object with question contents extracted from file 
    Parameter:
        obj: Exam object
    Returns:
        (obj, status): tuple containing updated Exam object and status
        where status: bool, True if exam is setup successfully. Otherwise, False.
    '''

    qus_file = open(obj.path_to_dir+'/questions.txt')
    extract_q = setup.extract_questions(qus_file)
    obj.set_questions(extract_q)
    obj.set_exam_status()
    q_stat = obj.exam_status

    return obj, q_stat


def main(args):
    '''
    Implement all stages of exam process.
    '''
    args_return = parse_cmd_args(args)
    if args_return != None:

        qus_file_path = args_return[0]+'/questions.txt'
        std_file_path = args_return[0]+'/students.csv'
        try:
            qus_file = open(qus_file_path)
            std_file = open(std_file_path)
        except:
            print('Missing files')
            sys.exit(1)
        duration = args_return[1]
        path_exam = args_return[0]
        shuffle = args_return[2]
        print('Setting up exam...')
        check_q = Exam(duration, path_exam, shuffle)

        exam_info = setup_exam(check_q)
        exam_stat = exam_info[1]
        exam_obj = exam_info[0]
        if exam_stat == False:
            print('Error setting up exam')
            sys.exit(1)
        else:
            print('Exam is ready...')
        while True:
            user_input = input("Do you want to preview the exam [Y|N]? ")
            user_input = user_input.upper().strip()
            if user_input == 'Y':
                pre = exam_obj.preview_exam()
                print(pre, end='')
            elif user_input == 'N':
                break
            else:
                print('Invalid command.')
        return exam_obj  # return for program_two
    else:
        sys.exit()


if __name__ == "__main__":
    '''
    DO NOT REMOVE
    '''
    main(sys.argv)

