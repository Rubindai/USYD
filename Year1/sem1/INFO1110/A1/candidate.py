from exam import Exam
impor os
import sys


class Candidate:
    def __init__(self, sid, name, time):
        self.sid = sid
        self.name = name
        self.extra_time = time
        self.exam = None
        self.confirm_details = False
        self.results = []

    def get_duration(self):
        '''
        Returns total duration of exam.
        '''
        total = self.exam.duration+self.extra_time
        return total
    def edit_sid(self, sid):
        '''
        Update attribute sid
        '''
        
        try:
            if len(sid) == 9:
                updated_sid = int(sid)
                self.sid = str(updated_sid)
        except:
            pass

    def edit_extra_time(self, t):
        '''
        Update attribute extra_time
        '''

        if type(t) == int and t >= 0:
            self.extra_time = t

    def set_confirm_details(self, sid, name):
        '''
        Update attribute confim_details
        '''
        if self.sid == sid and self.name == name:
            self.confirm_details = True

        return self.confirm_details

    def log_attempt(self, data):
        '''
        Save data into candidate's file in Submissions.
        '''

        path = self.exam.path_to_dir
        file_path = path+'/submissions/'+self.sid+'.txt'

        if os.path.isdir(path+'/submissions'):
            file_log = open(file_path, 'w')
            file_log.write(data)
            file_log.close()

        else:
            os.mkdir(path+'/submissions')
            file_log = open(file_path, 'w')
            file_log.write(data)
            file_log.close()

    def set_results(self, ls):
        '''
        Update attribute results if confirm_details are True
        '''
        if self.confirm_details == True:
            if len(ls) == len(self.exam.questions)-1:
                self.results = ls

    def do_exam(self, preview=True):
        '''
        Display exam and get candidate response from terminal during the exam.
        '''
        mark_list = []
        name = f"Candidate: {self.name}({self.sid})\n"
        t = self.get_duration()
        duration = f"Exam duration: {t} minutes\n"
        duration += "You have " + str(t) + " minutes to complete the exam.\n"
        exam_name = self.exam.get_name()
        str_out = name + duration + exam_name
        print(str_out)
        i = 0
        data = ''
        while i < len(self.exam.questions):

            ques_obj = self.exam.questions[i]

            str_pre = ''
            if ques_obj.qtype != None:
                if ques_obj.qtype == 'end':
                    str_pre = '-End-'
                elif ques_obj.answer_options != []:
                    if ques_obj.qtype == 'single':
                        str_pre = f'''Question {i+1} - {ques_obj.qtype.capitalize()} Answer[{ques_obj.marks}]
{ques_obj.description}
{ques_obj.get_answer_option_descriptions()}'''
                    else:

                        str_pre = f'''Question {i+1} - {ques_obj.qtype.capitalize()} Answers[{ques_obj.marks}]
{ques_obj.description}
{ques_obj.get_answer_option_descriptions()}'''
                elif ques_obj.answer_options == []:
                    str_pre = f'''Question {i+1} - {ques_obj.qtype.capitalize()} Answer[{ques_obj.marks}]
{ques_obj.description}'''

            print(str_pre)

            if ques_obj.qtype != 'end':
                if preview:
                    print(f'Response for Question {i+1}: \n')
                else:
                    user_input = input(f'Response for Question {i+1}: ')
                    print('')

                    marks = ques_obj.mark_response(user_input)
                    mark_list.append(marks)

                    data += (str_pre+f'\nResponse for Question {i+1}: ' +
                             user_input+f'\nYou have scored {marks:.2f} marks.\n\n')

            elif not preview:
                self.set_results(mark_list)
                data += (str_pre)
                self.log_attempt(data)

            i += 1

    def __str__(self):
        name = f"Candidate: {self.name}({self.sid})\n"
        t = self.set_duration()
        duration = f"Exam duration: {t} minutes\n"
        duration += "You have " + str(t) + " minutes to complete the exam.\n"
        if self.exam == None:
            exam = f"Exam preview: \nNone\n"
        else:
            exam = self.exam.preview_exam()
        str_out = name + duration + exam
        return str_out

