
from objects5 import *

# Modify the functions instance_to_formula, assignment_to_solution, solution_to_assignment! Do not change the function names or arguments.
# You may construct additional functions as desired.
# See objects5.py to learn what the objects contain and how to use and construct them.


def permutation_options(i, size, width, height):
    ls = []
    # ls.append(i)

    for x_cord in range(0, size):
        if (x_cord+width) <= size:
            for y_cord in range(0, size):
                if (y_cord+height) <= size:
                    ls.append((x_cord, y_cord, i, width, height))

    return ls


# def convert_single(list_of_atoms):
#     i = 0
#     while i < len(list_of_atoms):
#         list_of_atoms_copy = list_of_atoms.copy()
#         list_of_atoms_copy[i] = Negation(list_of_atoms_copy[0])


def helper(cord_1, cord_2):
    cord_1_upper_left_x = cord_1[0]
    cord_1_upper_left_y = cord_1[1]
    cord_1_upper_right_x = cord_1[0]+cord_1[3]-1
    cord_1_upper_right_y = cord_1_upper_left_y
    cord_1_lower_left_x = cord_1_upper_left_x
    cord_1_lower_left_y = cord_1[1]+cord_1[4]-1
    cord_1_lower_right_x = cord_1_upper_right_x
    cord_1_lower_right_y = cord_1_lower_left_y

    cord_2_upper_left_x = cord_2[0]
    cord_2_upper_left_y = cord_2[1]
    cord_2_upper_right_x = cord_2[0]+cord_2[3]-1
    cord_2_upper_right_y = cord_2_upper_left_y
    cord_2_lower_left_x = cord_2_upper_left_x
    cord_2_lower_left_y = cord_2[1]+cord_2[4]-1
    cord_2_lower_right_x = cord_2_upper_right_x
    cord_2_lower_right_y = cord_2_lower_left_y

    if cord_1_upper_left_x <= cord_2_upper_left_x <= cord_1_upper_right_x and cord_1_upper_right_y <= cord_2_upper_left_y <= cord_1_lower_left_y or \
            cord_1_upper_left_x <= cord_2_upper_right_x <= cord_1_upper_right_x and cord_1_upper_right_y <= cord_2_upper_right_y <= cord_1_lower_left_y or \
            cord_1_upper_left_x <= cord_2_lower_left_x <= cord_1_upper_right_x and cord_1_upper_right_y <= cord_2_lower_left_y <= cord_1_lower_left_y or \
            cord_1_upper_left_x <= cord_2_lower_right_x <= cord_1_upper_right_x and cord_1_upper_right_y <= cord_2_lower_right_y <= cord_1_lower_left_y or \
    cord_2_upper_left_x < cord_1_upper_left_x and cord_2_upper_right_x > cord_1_upper_right_x and cord_1_upper_left_y <= cord_2_upper_left_y <= cord_1_lower_left_y:
        return True

    return False


def overlap_calculate(ls):
    i = 0
    return_ls = []
    while i < len(ls):
        j = 0
        while j < len(ls):
            if (j == i):
                j += 1
                continue

            for k in ls[i]:
                for m in ls[j]:
                    # print((k, m))
                    if (helper(k, m)):
                        atom_1 = Atom(f"{k}")
                        atom_2 = Atom(f"{m}")
                        form_1 = Negation(atom_1)
                        form_2 = Negation(atom_2)
                        form = Disjunction([form_1, form_2])
                        return_ls.append(form)
            j += 1
        i += 1
    return return_ls


def instance_to_formula(instance):
    # input: Instance object
    # output: Formula object; must be in strict CNF: a Conjunction of Disjunctions of literals: Atom or Negation(Atom)
    size = instance.n
    list_tuple = instance.r
    ls = []
    for i, (width, height) in enumerate(list_tuple):
        ls.append(permutation_options(i, size, width, height))
    # print(ls)
    list_of_exclude = []
    if ls != [] and len(ls) > 1:
        list_of_exclude = overlap_calculate(ls)
    # print(list_of_exclude)
    list_of_atom = []
    list_of_disjunct = []
    for i in ls:
        if list_of_atom != []:
            form = Disjunction(list_of_atom)
            list_of_disjunct.append(form)
            # form2 =
            list_of_atom = []
        for j in i:
            atom = Atom(f"{j}")
            list_of_atom.append(atom)

    if list_of_atom != []:
        form = Disjunction(list_of_atom)
        list_of_disjunct.append(form)
        list_of_atom = []
    final_list = []
    for i in list_of_disjunct:
        final_list.append(i)

    for i in list_of_exclude:
        final_list.append(i)
    f = Conjunction(final_list)
    # print(f)
    return f


def assignment_to_solution(formula, assignment):
    # input: Formula object, Assignment object
    # The input Formula is returned from a previous call to instance_to_formula
    # output: Solution object

    ls = []
    set_of_assignment = assignment.s

    for i in set_of_assignment:
        # print(i)
        tmp = i[1:-2]
        tmp = tmp.split(", ")
        cord_tmp = (int(tmp[2]), int(tmp[0]), int(tmp[1]))

        ls.append(cord_tmp)

    ls.sort()
    final_ls = []
    for i in ls:
        tmp = i[1:]
        final_ls.append(tmp)

    return Solution(final_ls)


def solution_to_assignment(formula, solution):
    # input: Formula object, Solution object
    # The input Formula is returned from a previous call to instance_to_formula
    # output: Assignment object
    solution_set = solution.l
    set_of_atoms = formula.atoms_used()

    counter = 0
    ls = []

    for i in solution_set:

        for j in set_of_atoms:
            index = int(j[7:8])
            if (index == counter):
                width = int(j[10:11])
                height = int(j[13:14])
                x_cord = i[0]
                y_cord = i[1]
                ls.append(f"({x_cord}, {y_cord}, {
                          counter}, {width}, {height})")

                counter += 1

                break

    return Assignment(ls)

    # instance_to_formula(Instance(3, [(2, 2), (2, 1), (1, 2)]))

    # See run_tests.py for how to test your code locally
# print(helper((0, 2, 0, 5, 2), (0, 1, 1, 1, 4)))
