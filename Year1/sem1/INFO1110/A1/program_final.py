'''
Interface of the exam
'''
import os
import sys
import setup
import program_two
from candidate import Candidate


def main(args):
    extract_s = program_two.main(args)
    counter = 0
    counter2 = 0
    counter3 = 0
    counter_res = 0
    flag_complete = 0
    while True:
        if flag_complete:
            break
        flag_valid = 0
        flag_show = 1
        if counter == 3 or counter2 == 3 or counter_res == 4:
            print('Contact exam administrator.')
            sys.exit()
        user_input = input(
            'Enter your student identification number (SID) to start exam: ')

        if len(user_input) == 9:

            try:
                a = int(user_input)
                flag_valid = 1
            except:
                print('Invalid SID.')
                counter += 1

        else:
            print('Invalid SID.')
            counter += 1

        if flag_valid:

            i = 0
            flag_show2 = 1

            while i < len(extract_s):

                if user_input.strip() == extract_s[i].sid:
                    flag_show = 0

                    print('Verifying candidate details...')
                    while True:
                        user_input2 = input(
                            'Enter your full name as given during registration of exam: ')

                        if user_input2.lower() == extract_s[i].name.lower():

                            flag_show2 = 0
                            extract_s[i].set_confirm_details(
                                user_input, extract_s[i].name)

                            print('Start exam....')
                            print('')
                            extract_s[i].do_exam(False)
                            flag_complete = 1
                            break
                            # print('')
                            # path=extract_s[i].exam.path_to_dir
                            # file_path=path+'/submissions/'+extract_s[i].sid+'.txt'
                            # file_log=open(file_path,'r')

                            # print(os.listdir('/home/info1110_test_1/submissions'))
                            # print(sys.argv[0])

                            # while True:
                            #     print(files.readline())
                            #     if a=='':
                            #         break

                        else:
                            if counter3 == 2:
                                print(
                                    'Contact exam administrator to verify documents.')
                                sys.exit()
                            print('Name does not match records.')
                            counter3 += 1

                i += 1
                if i == len(extract_s) and flag_show and not flag_complete:
                    print('Candidate number not found for exam.')
                    counter2 += 1

                    while counter_res <= 4:
                        user_input3 = input(
                            'Do you want to try again [Y|N]? ').lower()
                        counter_res += 1

                        if counter <= 2 and counter_res < 4:
                            if user_input3 == 'y':
                                counter_res = 0
                                break
                            elif user_input3 == 'n':
                                sys.exit()
                            else:
                                print('Response must be [Y|N].')
                        else:
                            break


if __name__ == "__main__":
    '''
    DO NOT REMOVE
    '''
    main(sys.argv)

