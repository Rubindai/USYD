'''
Functions to setup the exam questions and candidate list for the exam.
'''
# please do not change or add another import
from question import Question
from candidate import Candidate
import io


def extract_questions(fobj: io.TextIOWrapper) -> list:
    """
    Parses fobj to extract details of each question found in the file.
    General procedure to extract question.
    1. Extract the following
        - type
        - question details (description)
        - possible answers (if any)
        - expected answer
        - marks
        (you shouldn't need to perform error handling on these details,
        this is handled in the next step).
    2. You'll need to convert the possible answers (if any) to a list of tuples (see 
       "Section 1. Setup the exam - Question" for more details). All flags can be False.
    3. Create a question object and call the instance methods to set the
       attributes. This will handle the error handling.
    4. Repeat Steps 1-3 for the next question until there are no more questions.
    5. You will need to create an end question as well.
    6. Create the list for all your questions and return it.

    Parameter:
        fobj: open file object in read mode
    Returns:
        result: list of Question objects.
    """
    i = 0
    desc = ''
    flag1 = 1
    flag_ans = 0
    ans_opts = []
    flag_exp = 0
    flag_mark = 0
    read_corect_ans = ''
    flag2 = 1
    ans_opts_list = [0, False]
    ques_obj_list = []
    flag_stop = 0

    while True:
        line = fobj.readline()

        if line == '':  # EOF
            desc = desc.strip()
            # ques_obj=Question(qtype)
            ques_obj = create_quesz_obj(
                qtype, read_corect_ans, ans_opts, desc, read_mark)
            ques_obj_list.append(ques_obj)

            break
        i += 1
        if i == 1:
            qtype = readtype(line)
            qtype = qtype.lower().strip()

        if qtype != 'short' and i > 1:
            if line[0:17] == 'Possible Answers:':
                flag1 = 0
            if flag1:
                desc += line  # strore description until possible answers
        if line[0:17] == 'Possible Answers:':
            flag_ans = 1
            flag_stop = 1
            continue

        if qtype == 'short' and i > 1 and flag_stop == 0:
            try:
                if line[0:16] == 'Expected Answer:':
                    flag1 = 0
            except:
                pass
            if flag1:
                desc += line

        try:
            if line[0:16] == 'Expected Answer:':
                read_corect_ans = line[17:].strip()
                flag_exp = 1
        except:
            pass

        if flag_exp == 0 and flag_ans:
            ans_opts_list[0] = line.strip()
            ans_opts_tuple = tuple(ans_opts_list)
            ans_opts.append(ans_opts_tuple)

        if flag_exp == 1 and flag2:
            flag_mark = 1
            flag2 = 0
            continue
        if flag_mark:
            read_mark = line[6:].strip()

            read_mark = int(read_mark)
            flag_mark = 0
        if line == '\n':
            desc = desc.strip()
            ques_obj = create_quesz_obj(
                qtype, read_corect_ans, ans_opts, desc, read_mark)
            ques_obj_list.append(ques_obj)
            i = 0
            desc = ''
            flag1 = 1
            flag_ans = 0
            ans_opts = []
            flag_exp = 0
            flag_mark = 0
            read_corect_ans = ''
            flag2 = 1
            ans_opts_list = [0, False]
            flag_stop = 0
    ques_obj = Question('end')
    ques_obj_list.append(ques_obj)
    return ques_obj_list


def sort(to_sort: list, order: int = 0) -> list:
    """
    Sorts to_sort depending on settings of order.

    Parameters:
        to_sort: list, list to be sorted.
        order: int, 0 - no sort, 1 - ascending, 2 - descending
    Returns
        result: list, sorted results.

    Sample usage:
    >>> to_sort = [(1.50, "orange"), (1.02, "apples"), (10.40, "strawberries")]
    >>> print("Sort 0:", sort(to_sort, 0))
    Sort 0: [(1.5, 'orange'), (1.02, 'apples'), (10.4, 'strawberries')]
    >>> print("Sort 1:", sort(to_sort, 1))
    Sort 1: [(1.02, 'apples'), (1.5, 'orange'), (10.4, 'strawberries')]
    >>> print("Sort 2:", sort(to_sort, 2))
    Sort 2: [(10.4, 'strawberries'), (1.5, 'orange'), (1.02, 'apples')]
    >>> to_sort = [ "oranges", "apples", "strawberries"]
    >>> print("Sort 0:", sort(to_sort, 0))
    Sort 0: ['oranges', 'apples', 'strawberries']
    >>> print("Sort 1:", sort(to_sort, 1))
    Sort 1: ['apples', 'oranges', 'strawberries']
    >>> print("Sort 2:", sort(to_sort, 2))
    Sort 2: ['strawberries', 'oranges', 'apples']
    """

    if order == 0:
        pass

    if order == 1:
        i = 0
        while i < len(to_sort):
            k = 0
            while k+1 < len(to_sort):
                if to_sort[k] > to_sort[k+1]:
                    a = to_sort[k+1]
                    b = to_sort[k]
                    to_sort[k] = a
                    to_sort[k+1] = b
                k += 1
            i += 1
    if order == 2:
        i = 0
        while i < len(to_sort):
            k = 0
            while k+1 < len(to_sort):
                if to_sort[k] < to_sort[k+1]:
                    a = to_sort[k+1]
                    b = to_sort[k]
                    to_sort[k] = a
                    to_sort[k+1] = b
                k += 1
            i += 1

    return to_sort


def extract_students(fobj: io.TextIOWrapper) -> list:
    """
    Parses fobj to extract details of each student found in the file.

    Parameter:
        fobj: open file object in read mode
    Returns:
        result: list of Candidate objects sorted in ascending order
    """
    i = 0
    list_of_candidate = []
    while True:
        try:
            read_file = fobj.readline()
            i += 1
        except:
            list_of_candidate = []
            return list_of_candidate

        if i == 1:
            continue
        if read_file == '':
            if i == 1:
                list_of_candidate = []
                return list_of_candidate
            break
        read_list = read_file.split(',')
        try:
            sid = read_list[0]
            name = read_list[1]
            time = read_list[2]
        except:
            list_of_candidate = []
            return list_of_candidate
        if time == '\n' or time == '':
            time = 0

        try:
            time = int(time)
        except:
            list_of_candidate = []
            return list_of_candidate

        candidate_obj = Candidate(sid, name, time)
        list_of_candidate.append(candidate_obj)
    i = 0
    temp_list = []
    while i < len(list_of_candidate):
        temp_list.append(int(list_of_candidate[i].sid))
        i += 1
    sorted_list = sort(temp_list, 1)

    i = 0
    sorted_list_candidate = [0]*len(list_of_candidate)

    while i < len(sorted_list):
        j = 0

        while j < len(sorted_list):
            if list_of_candidate[j].sid == str(sorted_list[i]):

                sorted_list_candidate[i] = list_of_candidate[j]

            j += 1
        i += 1
    return sorted_list_candidate


def readtype(line):
    readtype = line.split(' - ')
    readtype = readtype[1]
    return readtype


def create_quesz_obj(qtype, read_corect_ans, ans_opts, desc, read_mark):
    ques_obj = Question(qtype)
    ques_obj.set_correct_answer(read_corect_ans)
    ques_obj.set_answer_options(ans_opts)
    ques_obj.set_description(desc)
    ques_obj.set_marks(read_mark)
    return ques_obj


if __name__ == '__main__':
    # a=open('/home/rubin/ed/info1110/assignment1/questions.txt')
    # a=extract_questions(a)
    # print(a[0].answer_options)
    b = open('/home/rubin/ed/info1110/assignment1/students.csv')
    c = extract_students(b)
    print(c[0].name, c[1].name, c[2].name, c[3].name, c[4].name)

