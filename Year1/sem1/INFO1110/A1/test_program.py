'''
Write instructions to execute your test program here.
'''
import unittest
import question


class TestMarkResponse(unittest.TestCase):
    def test_short_valid_mark(self):

        ques_obj = question.Question('short')
        ques_obj.set_correct_answer('Bob')
        ques_obj.set_description('After running the program, you have keyed in "Bob" followed by the Enter key.\
What is the value stored in variable name?')
        ques_obj.set_marks(2)
        user_input = 'Bob'
        actual = ques_obj.mark_response(user_input)
        expected = 2
        self.assertEqual(actual, expected)

    def test_short_valid_nomark(self):

        ques_obj = question.Question('short')
        ques_obj.set_correct_answer('Bob')
        ques_obj.set_description('After running the program, you have keyed in "Bob" followed by the Enter key.\
What is the value stored in variable name?')
        ques_obj.set_marks(2)
        user_input = 'kimm'
        actual = ques_obj.mark_response(user_input)
        expected = 0
        self.assertEqual(actual, expected)

    def test_short_invalid(self):

        ques_obj = question.Question('short')
        ques_obj.set_correct_answer('Bob')
        ques_obj.set_description('After running the program, you have keyed in "Bob" followed by the Enter key.\
What is the value stored in variable name?')
        ques_obj.set_marks(2)
        user_input = 123
        actual = ques_obj.mark_response(user_input)
        expected = 0
        self.assertEqual(actual, expected)

    def test_single_valid_mark(self):
        ques_obj = question.Question('single')
        ques_obj.set_correct_answer('A')
        ques_obj.set_answer_options([('A. print("I love INFO1110")', False), ('B. print("I" "love" "INFO1110")', False), (
            'C. print("I love" "INFO1110")', False), ('D. print(I love INFO1110)', False)])
        ques_obj.set_description('Which of the following Python statements will display this message in the terminal?\
*********************************\
Terminal output:\
<Start>I love INFO1110<End>\
*********************************')
        ques_obj.set_marks(2)
        user_input = 'A'
        actual = ques_obj.mark_response(user_input)
        expected = 2
        self.assertEqual(actual, expected)

    def test_single_valid_nomark(self):
        ques_obj = question.Question('single')
        ques_obj.set_correct_answer('A')
        ques_obj.set_answer_options([('A. print("I love INFO1110")', False), ('B. print("I" "love" "INFO1110")', False), (
            'C. print("I love" "INFO1110")', False), ('D. print(I love INFO1110)', False)])
        ques_obj.set_description('Which of the following Python statements will display this message in the terminal?\
*********************************\
Terminal output:\
<Start>I love INFO1110<End>\
*********************************')
        ques_obj.set_marks(2)
        user_input = 'A,B'
        actual = ques_obj.mark_response(user_input)
        expected = 0
        self.assertEqual(actual, expected)

    def test_single_invalid(self):
        ques_obj = question.Question('single')
        ques_obj.set_correct_answer('A')
        ques_obj.set_answer_options([('A. print("I love INFO1110")', False), ('B. print("I" "love" "INFO1110")', False), (
            'C. print("I love" "INFO1110")', False), ('D. print(I love INFO1110)', False)])
        ques_obj.set_description('Which of the following Python statements will display this message in the terminal?\
*********************************\
Terminal output:\
<Start>I love INFO1110<End>\
*********************************')
        ques_obj.set_marks(2)
        user_input = 122
        actual = ques_obj.mark_response(user_input)
        expected = 0
        self.assertEqual(actual, expected)

    def test_multiple_valid_mark(self):
        ques_obj = question.Question('multiple')
        ques_obj.set_correct_answer('B, C, D')
        ques_obj.set_answer_options([('A. Ankit', False), ('B. Ehab', False), (
            'C. Joshua', False), ('D. Whoever marks my paper', False)])
        ques_obj.set_description('Who is the best tutor in INFO1110')
        ques_obj.set_marks(2)
        user_input = 'B, C, D'
        actual = ques_obj.mark_response(user_input)
        expected = 2
        self.assertEqual(actual, expected)

    def test_multiple_valid_nomark(self):
        ques_obj = question.Question('multiple')
        ques_obj.set_correct_answer('B, C, D')
        ques_obj.set_answer_options([('A. Ankit', False), ('B. Ehab', False), (
            'C. Joshua', False), ('D. Whoever marks my paper', False)])
        ques_obj.set_description('Who is the best tutor in INFO1110')
        ques_obj.set_marks(2)
        user_input = 'A, B'
        actual = ques_obj.mark_response(user_input)
        expected = 0.67
        self.assertEqual(actual, expected)

    def test_multiple_invalid(self):
        ques_obj = question.Question('multiple')
        ques_obj.set_correct_answer('B, C, D')
        ques_obj.set_answer_options([('A. Ankit', False), ('B. Ehab', False), (
            'C. Joshua', False), ('D. Whoever marks my paper', False)])
        ques_obj.set_description('Who is the best tutor in INFO1110')
        ques_obj.set_marks(2)
        user_input = 122
        actual = ques_obj.mark_response(user_input)
        expected = 0
        self.assertEqual(actual, expected)


if __name__ == "__main__":
    unittest.main()

