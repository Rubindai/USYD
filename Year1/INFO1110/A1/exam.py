import os
from question import Question


class Exam:
    def __init__(self, duration, path, shuffle):
        self.duration = duration
        self.path_to_dir = path
        self.shuffle = shuffle
        self.exam_status = False
        self.questions = []
        self.set_name(path)

    def set_name(self, path):
        """
        Sets the name of the exam. 
        """

        updated_path = os.path.basename(path)
        i = 0
        str_new = ''
        while i < len(updated_path):
            if updated_path[i] == ' ':
                str_new += '_'
            else:
                str_new += updated_path[i]
            i += 1

        # you'll need to add some code here
        self.name = str_new

    def get_name(self):
        """
        Returns formatted string of exam name.
        """
        str_new = self.name
        display_name = ''
        i = 0
        while i < len(str_new):
            if str_new[i] == '_':
                display_name += ' '
            else:
                display_name += str_new[i]
            i += 1
        display_name = display_name.upper()
        return display_name

    def set_exam_status(self):
        '''
        Set exam_status to True only if exam has questions.
        '''
        if self.questions != []:
            self.exam_status = True

    def set_duration(self, t):
        '''
        Update duration of exam.
        Parameter:
            t: int, new duration of exam.
        '''
        if type(t) == int and t > 0:
            self.duration = t

    def set_questions(self, ls):
        '''
        Verifies all questions in the exam are complete.
        Parameter:
            ls: list, list of Question objects
        Returns:
            status: bool, True if set successfully.
        '''
        i = 0
        if len(ls) == 0:

            return False

        while i < len(ls):
            try:
                qtype = ls[i].qtype

            except:

                return False

            if i < len(ls)-1:
                # if self.shuffle==True:
                #     ls[i].shuffle_answers()
                if qtype == 'end':
                    print("End marker missing or invalid")
                    return False

                if ls[i].description == None or ls[i].correct_answer == None:
                    print("Description or correct answer missing")
                    return False
                if ls[i].answer_options == [] and qtype != 'short':  # attention

                    print("Answer options incorrect quantity")
                    return False
                if ls[i].answer_options != [] and qtype == 'short':
                    print("Answer options should not exist")
                    return False

            if i == len(ls)-1 and (qtype != 'end' or (qtype == 'end' and (ls[i].answer_options != [] or ls[i].description != None or ls[i].correct_answer != None or ls[i].marks != None))):
                print("End marker missing or invalid")
                return False

            i += 1

        self.questions = ls
        return True

    def preview_exam(self):
        '''
        Returns a formatted string.
        '''
        str_pre = f'{self.get_name()}\n'
        i = 0
        while i < len(self.questions):
            str_pre += self.questions[i].preview_question(i+1)+'\n'+'\n'
            i += 1
        return str_pre

    def copy_exam(self):
        '''
        Create a new exam object using the values of this instances' values.
        '''
        # TODO: make a new exam object (call the constructor)
        # print(f'check {self.questions[0].answer_options}')
        new_exam = Exam(self.duration, self.path_to_dir, self.shuffle)

        # make a new list of questions to reassign to the attribute
        new_questions = []
        i = 0
        while i < len(self.questions):
            original_question = self.questions[i]
            # print(f'check3 {self.questions[0].answer_options}')
            # call the copy method for this question
            # TODO: (you'll need to write this instance method in Question)
            new_question = original_question.copy_question()

            # print(f'check2 {self.questions[0].answer_options}')

            new_question.shuffle_answers()
            # print(new_question.answer_options)
            # print(new_question.answer_options)
            # insert this into new list of questions
            new_questions.append(new_question)

            i += 1

        # TODO: assign this new question list to the new exam
        # (replace this comment with code)
        new_exam.questions = new_questions.copy()

        # return the new exam
        return new_exam

    def __str__(self):
        pass


if __name__ == '__main__':
    a = Question('single')
    b = Question('end')
    # b.answer_options=[1]
    ls = [('A. print("I love INFO1110")', False), ('B. print("I" "love" "INFO1110")', False),
          ('C. print("I love" "INFO1110")', False), ('D. print(I love INFO1110)', False)]
    a.set_correct_answer('A')
    a.set_answer_options(ls)

    a.set_description("1110")
    a.set_marks(1)
    c = Exam(1, 'b', 'c')
    print(c.set_questions([a, b]))
    c.copy_exam()

