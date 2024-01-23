'''
Interface of the exam
'''

import setup
import program_one
import sys

from question import Question
from exam import Exam
from candidate import Candidate


def assign_exam(exam):
    # print(exam.questions[0].answer_options)
    path = exam.path_to_dir
    path_student = path+'/students.csv'
    student_fobj = open(path_student)
    extract_s = setup.extract_students(student_fobj)

    if extract_s == []:
        print('No candidates found in the file')
        return None
    else:
        print('Assigning exam to candidates...')
        i = 0

        while i < len(extract_s):
            if exam.shuffle == True:
                extract_s[i].exam = exam.copy_exam()

                # extract_s[i].exam=exam
                # print(exam.questions[0].answer_options)
                # print(exam.questions[0].answer_options)

            if exam.shuffle == False:
                extract_s[i].exam = exam

            i += 1
        # print(exam.questions[0].answer_options)
        # print(extract_s[0].exam.questions[0].answer_options)
        # print(extract_s[1].exam.questions[0].answer_options)
        print(f'Complete. Exam allocated to {i} candidates.')
        return extract_s


def main(args):
    '''
    Implement all stages of exam process.
    You can import functions from program_one to avoid duplication of code.
    '''
    exam_obj = program_one.main(args)
    extract_s = assign_exam(exam_obj)

    while True:
        flag_valid = 0
        flag_show = 1
        user_input = input(
            'Enter SID to preview student\'s exam (-q to quit): ')
        if user_input == '-q':
            return extract_s
        elif user_input == '-a':
            i = 0
            while i < len(extract_s):
                to_print = (extract_s[i].do_exam())

                print('')
                i += 1
        elif len(user_input) == 9:
            try:
                a = int(user_input)
                flag_valid = 1
            except:
                print('SID is invalid.\n')

        else:
            print('SID is invalid.\n')

        if flag_valid:
            i = 0
            while i < len(extract_s):
                if user_input == extract_s[i].sid:
                    to_print = (extract_s[i].do_exam())
                    print('')
                    flag_show = 0
                i += 1
                if i == len(extract_s) and flag_show:
                    print('SID not found in list of candidates.\n')


if __name__ == "__main__":
    '''
    DO NOT REMOVE
    '''
    main(sys.argv)

