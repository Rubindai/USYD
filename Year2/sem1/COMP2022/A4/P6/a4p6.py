from formulas6 import Formula, Disjunction, Conjunction, Negation, Atom, Verum, Falsum
import random  # For use in testing - you can use this in your solution if desired but it is not recommended

# Modify the functions part1 and part2! Do not change the function names or arguments.
# You may construct additional functions as desired.


def part1(history):
    # Input: a tuple of 3-tuples each of the form (int: guard_asked, Formula: formula_asked, bool: response_from_guard)
    # Output: either a boolean True/False (if you think you know the answer) or a tuple (int: guard_to_ask, Formula: formula_to_ask)
    # guard number is an int 1, 2 or 3
    # formula is an arbitrary Formula object (see formulas.py) (but cannot mention the variables of the guard asked)

    # In this simple example, the program asks guard 1 whether r is true, and trusts the answer

    # If there were no prior questions (i.e. this is the first question)...
    if len(history) == 0:
        return (1, Atom("r"))  # Ask guard 1 if r is true
    # Otherwise (there was a prior question), look at guard 1's answer
    answer = history[0][2]
    return answer  # and trust it


# -> tuple[Literal[1], Atom] | tuple[Literal[2], Atom] | tuple...:
def part2(history):
    # Same format as part1

    # In this second example, the program asks guard 1 whether guard 2 tells the truth or not.
    # Based on that answer, it then either asks guard 2 what r is, or what ~r is.
    # It then trusts guard 2's answer to be the value of r.

    if len(history) == 0:  # First question
        return (1, Atom("t2"))

    if len(history) == 1:  # Second question
        guard1answer = history[0][2]
        if guard1answer:  # guard 1 says guard 2 always tells the truth
            return (2, Atom("r"))
        else:  # guard 1 says guard 2 doesnt always tell the truth
            return (2, Negation(Atom("r")))

    # We have already asked 2 questions, we now make a decision
    guard2answer = history[1][2]
    return guard2answer


if __name__ == "__main__":
    # You may add testing code here (this will not be run during grading)
    # A sample test, corresponding to the public test case on GS, is provided
    # You may wish to add more

    # makes sure a guard can't be asked about themself
    invalid_atoms = [[], ["t1", "f1"], ["t2", "f2"], ["t3", "f3"]]
    assignment = set(["f1", "t2", "t3", "r"])  # sample test case
    max_questions = 20
    history = []
    for questions in range(max_questions+1):
        # gives the function 'read-only' access to history
        response = part1(tuple(history))
        if type(response) == bool:
            print(f"Code output {response}. Expected True. Test {
                  'passed' if response else 'failed'}. Took {questions} questions.")
            break
        else:
            if questions == max_questions:
                print(
                    "Code failed to give an answer in {max_questions} questions. Test failed.")
                break
            if not type(response) == tuple:
                print(
                    "Test errored. Your function should return either a boolean or tuple. History: {history}")
                break
            if not len(response) == 2:
                print(
                    "Test errored. Your function returned a tuple with != 2 elements. History: {history}")
                break
            if not response[0] in (1, 2, 3):
                print(f"Test errored. Your function returned a tuple with 1st element not 1, 2 or 3. History: {
                      history}")
                break
            if not isinstance(response[1], Formula):
                print(f"Test errored. Your function returned a tuple with 2nd element not a Formula, but a {
                      type(response[1])}. History: {history}")
                break
            if not len(response[1].atoms_used().intersection(invalid_atoms[response[0]])) == 0:
                print(f"Test errored. Your function returned a formula using an invalid atom (asking a guard about themself). History: {
                      history}")
                break
            trueanswer = response[1].evaluate(assignment)
            # checks whether guard asked tells the truth
            if [None, "t1", "t2", "t3"][response[0]] in assignment:
                answer = trueanswer
            elif [None, "f1", "f2", "f3"][response[0]] in assignment:
                answer = not trueanswer
            else:  # if neither t_i nor f_i is true, the guard answers randomly
                answer = random.choice([True, False])
# Note: In tests with a random guard you may wish to rerun the test several times to ensure your program always works, regardless of the random guard's answers
            history.append([response[0], response[1], answer])
    print("Test done")
