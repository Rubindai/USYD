# Defines various objects used in P5. DO NOT MODIFY THIS FILE


class Instance:
    def __init__(self, n, r):
        # You will not need to construct this class in your solution, but may wish to for testing
        # n (int) is n
        # r (list) contains 2-tuples (int, int) of the rectangle sizes (x-width, y-height)
        self.n = n
        self.r = r


class Assignment:
    def __init__(self, s):
        # You will need to construct this in your solution.
        # This class is basically just a fancy set (the true variables in the assignment)
        # Construct it using any collection (list, set, tuple, etc.)
        self.s = set(s)


class Solution:
    def __init__(self, l):
        # You will need to construct this in your solution.
        # This class is a (ordered) list of 2-tuples (x_position, y_position)
        # The i-th tuple gives the position of the i-th rectangle in the solution
        # Horizontal axis is first (just as, in Instances, horizontal width is first)
        assert type(l) == list, "Solution should be made from a list"
        for i in l:
            assert type(
                i) == tuple, "Solution's list's elements should be tuples"
            assert len(i) == 2, "Solution's list's tuples should have length 2"
        self.l = l


# For Formulas: The only method you will need is the constructors
# Construct atoms as Atom("a"), Atom("b"), etc. Any string may be used as an atom name, and you will likely want to include numbers.
# Construct larger formulas as Negation(k1), Disjunction([k1,k2]), Conjunction([k1,k2,k3,k4,k5]) where ki are Formula objects
# The constructed formula must be in CNF: a Conjunction of Disjunctions of literals: Atom or Negation(Atom)
# If you want a clause with only 1 literal you can make it a Disjunction of a single formula with Disjunction([k1])

class Formula:
    pass


class Disjunction(Formula):
    def __init__(self, forms):
        for i in forms:
            assert isinstance(
                i, Formula), "Disjunction must take a list of formulas"
        self.forms = forms

    def __repr__(self):
        return "(" + "|".join(map(lambda x: x.__repr__(), self.forms)) + ")"

    def atoms_used(self):
        return set.union(*map(lambda x: x.atoms_used(), self.forms))

    def evaluate(self, assignment):
        for i in self.forms:
            if i.evaluate(assignment):
                return True
        return False


class Conjunction(Formula):
    def __init__(self, forms):
        for i in forms:
            assert isinstance(
                i, Formula), "Conjunction must take a list of formulas"
        self.forms = forms

    def __repr__(self):
        return "(" + "&".join(map(lambda x: x.__repr__(), self.forms)) + ")"

    def atoms_used(self):
        return set.union(*map(lambda x: x.atoms_used(), self.forms))

    def evaluate(self, assignment):
        for i in self.forms:
            if not i.evaluate(assignment):
                return False
        return True


class Negation(Formula):
    def __init__(self, form):
        assert isinstance(form, Formula), "Negation must take a single formula"
        self.form = form

    def __repr__(self):
        return "~" + self.form.__repr__()

    def atoms_used(self):
        return self.form.atoms_used()

    def evaluate(self, assignment):
        return not self.form.evaluate(assignment)


class Atom(Formula):
    def __init__(self, value):
        assert type(value) == str, "Atoms should be strings"
        self.value = value

    def __repr__(self):
        return self.value

    def atoms_used(self):
        return set([self.value])

    def evaluate(self, assignment):
        return self.value in assignment.s
