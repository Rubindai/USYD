import random


class Question:

    def __init__(self, qtype):

        self.set_type(qtype)
        self.description = None
        self.answer_options = []
        self.correct_answer = None
        self.marks = None

    def set_type(self, qtype):
        """
        Update instance variable qtype.
        """
        if qtype == 'single' or qtype == 'multiple' or qtype == 'short' or qtype == 'end':
            self.qtype = qtype
            return True
        self.qtype = None
        return False

    def set_description(self, desc):
        """
        Update instance variable description.
        """
        if self.qtype != 'end' and type(desc) == str and desc != '' and self.qtype != None:
            self.description = desc
            return True
        return False

    def set_correct_answer(self, ans):
        """
        Update instance variable correct_answer.
        """

        if self.qtype != 'end' and type(ans) == str:
            if self.qtype == 'single' and (ans == 'A' or ans == 'B' or ans == 'C' or ans == 'D'):
                self.correct_answer = ans
                return True
            if self.qtype == 'multiple':
                str1 = ans.split(',')

                i = 0
                while i < len(str1):
                    str1[i] = str1[i].strip()
                    if str1[i] != 'A' and str1[i] != 'B' and str1[i] != 'C' and str1[i] != 'D':
                        return False

                    i += 1
                self.correct_answer = ans
                return True
            if self.qtype == 'short':
                self.correct_answer = ans
                return True
        return False

    def set_marks(self, num):
        """
        Update instance variable marks.
        """
        if self.qtype == 'end':
            return False
        if type(num) == int and num >= 0:
            self.marks = num
            return True
        return False

    def set_answer_options(self, opts):
        """
        Update instance variable answer_options.

        opts should have all flags equal to False when passed in.
        This method will update the flags based on the correct answer.
        Only then do we check that the number of correct answers is correct.
        """
        if self.qtype == 'short' or self.qtype == 'end':
            self.answer_options = opts
            return True
        i = 0
        if type(opts) != list:
            return False
        if len(opts) != 4:
            return False

        while i < len(opts):
            if_tuple = type(opts[i])
            if_two = len(opts[i])
            if if_tuple != tuple or if_two != 2:
                return False
            i += 1
        i = 0
        while i < len(opts):
            options = opts[i][0]
            boolen_check = opts[i][1]
            options = options[0:2]
            if (i == 0 and options != 'A.') or (i == 1 and options != 'B.') or (i == 2 and options != 'C.') or (i == 3 and options != 'D.'):
                return False
            if boolen_check != False:
                return False
            i += 1
        if self.correct_answer != None:
            if self.qtype == 'single':
                i = 0
                while i < len(opts):
                    options = opts[i][0]
                    if options[0] == self.correct_answer:
                        temp = list(opts[i]) #convert it to list and then change the value
                        temp[1] = True
                        temp = tuple(temp) #convert it back to tuple
                        opts[i] = temp
                    i += 1
            if self.qtype == 'multiple':

                j = 0
                str2 = self.correct_answer.split(', ')
                while j < len(str2):
                    correct_answer_temp = str2[j]
                    i = 0
                    while i < len(opts):
                        options = opts[i][0]
                        if options[0] == correct_answer_temp:
                            temp = list(opts[i])
                            temp[1] = True
                            temp = tuple(temp)
                            opts[i] = temp
                        i += 1
                    j += 1
            self.answer_options = opts
            return True
        return False

    def get_answer_option_descriptions(self):
        """
        Returns formatted string listing each answer description on a new line.
        Example:
        A. Answer description
        B. Answer description
        C. Answer description
        D. Answer description
        """
        i = 0
        str3 = ''
        while i < len(self.answer_options):
            if i < len(self.answer_options)-1:
                str3 += (self.answer_options[i][0])+'\n'
            else:
                str3 += (self.answer_options[i][0])

            i += 1
        return str3

    def mark_response(self, response):
        """
        Check if response matches the expected answer
        Parameter:
            response: str, response provided by candidate
        Returns:
            marks: int|float, marks awarded for the response.
        """
        mark = int(self.marks)
        if not isinstance(response, str):
            return 0
        if self.qtype == 'end':
            return None
        if self.qtype == 'single' or self.qtype == 'short':
            if response == self.correct_answer:
                return mark
            else:
                return 0
        if self.qtype == 'multiple':
            i = 0
            j = 0
            correct_answer_list = self.correct_answer.split(', ')

            response_list = response.split(', ')

            partial_mark = (self.marks/len(correct_answer_list))

            total_mark = 0
            while i < len(correct_answer_list):
                j = 0
                while j < len(response_list):
                    if correct_answer_list[i] == response_list[j]:
                        total_mark += partial_mark
                    j += 1
                i += 1
            marks = float(f'{total_mark:.2f}')
            return marks

    def preview_question(self, i=0, show=True):
        """
        Returns formatted string showing details of question.
        Parameters:
            i: int, placeholder for question number, DEFAULT = 0
            show: bool, True to show Expected Answers, DEFAULT = TRUE
        """
        if show == True:
            if i == 0:
                i = 'X'
            str_pre = ''
            if self.qtype != None:
                if self.qtype == 'end':
                    str_pre = '-End-'
                elif self.answer_options != []:
                    if self.qtype == 'single':
                        str_pre = f'''Question {i} - {self.qtype.capitalize()} Answer[{self.marks}]
{self.description}
{self.get_answer_option_descriptions()}
Expected Answer: {self.correct_answer}'''
                    else:

                        str_pre = f'''Question {i} - {self.qtype.capitalize()} Answers[{self.marks}]
{self.description}
{self.get_answer_option_descriptions()}
Expected Answer: {self.correct_answer}'''
                elif self.answer_options == []:
                    str_pre = f'''Question {i} - {self.qtype.capitalize()} Answer[{self.marks}]
{self.description}
Expected Answer: {self.correct_answer}'''
            return str_pre

    def generate_order():
        """
        Returns a list of 4 integers between 0 and 3 inclusive to determine order.

        Sample usage:
        >>> generate_order()
            [3,1,0,2]
        """

        i = 0
        ran_list = [5]*4
        while i <= 3:
            num = random.randint(0, 3)
            if ran_list[0] != num and ran_list[1] != num and ran_list[2] != num and ran_list[3] != num:

                ran_list[i] = num
                i += 1
        return ran_list

    def shuffle_answers(self):
        """
        Updates answer options with shuffled elements.
        Must call generate_order only once.
        """

        if (self.qtype == 'single' or self.qtype == 'multiple') and len(self.answer_options) == 4:

            i = 0

            ran_list = Question.generate_order()

            temp_list = [0]*4
            updated_ans = ''
            while i < len(ran_list):

                temp_list[i] = self.answer_options[ran_list[i]]
                if i == 0:
                    options = 'A'
                if i == 1:
                    options = 'B'
                if i == 2:
                    options = 'C'
                if i == 3:
                    options = 'D'

                temp = list(temp_list[i])
                temp[0] = options+temp[0][1:]
                temp = tuple(temp)
                temp_list[i] = temp
                if temp_list[i][1] == True:
                    updated_ans += options

                i += 1

            i = 0
            updated_ans2 = ''
            while i < len(updated_ans):
                if i < len(updated_ans)-1:
                    updated_ans2 += updated_ans[i]+', '
                else:
                    updated_ans2 += updated_ans[i]
                i += 1
            self.correct_answer = updated_ans2
            self.answer_options = temp_list

    def copy_question(self):
        ques_obj = Question(self.qtype)

        ques_obj.correct_answer = self.correct_answer
        ques_obj.answer_options = self.answer_options
        ques_obj.description = self.description
        ques_obj.marks = self.marks
        return ques_obj

    def __str__(self):
        '''
        You are free to change this, this is here for your convenience.
        When you print a question, it'll print this string.
        '''
        return f'''Question {self.__hash__()}:
Type: {self.qtype}
Description: {self.description}
Possible Answers: {self.get_answer_option_descriptions()}
Correct answer: {self.correct_answer}
Marks: {self.marks}
'''


if __name__ == '__main__':
    a = Question('single')

    ls = [('A. print(I love INFO1110)', False), ('B. print("I" "love" "INFO1110")', False),
          ('C. print("I love" "INFO1110")', False), ('D. print("I love INFO1110")', False)]
    a.set_correct_answer('A')
    a.set_answer_options(ls)
    a.set_description('asd')
    a.set_marks(1)
    a.shuffle_answers()
    print(a.answer_options)
    # print(a.answer_options)

