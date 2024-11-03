#Defines boolean formulas. DO NOT MODIFY THIS FILE

#The only method you will need is the constructors
#Construct atoms as Atom("t1"), Atom("f2"), Atom("r"), etc.
#Construct larger formulas as Negation(k1), Disjunction([k1,k2]), Conjunction([k1,k2,k3,k4,k5]) where ki are Formula objects
#Also available are Verum() and Falsum()

class Formula:
    pass

class Disjunction(Formula):
    def __init__(self, forms):
        for i in forms:
            assert isinstance(i, Formula), "Disjunction must take a list of formulas"
        self.forms = forms
    def __repr__(self):
        return "(" + "|".join(map(lambda x: x.__repr__(),self.forms)) + ")"
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
            assert isinstance(i, Formula), "Conjunction must take a list of formulas"
        self.forms = forms
    def __repr__(self):
        return "(" + "&".join(map(lambda x: x.__repr__(),self.forms)) + ")"
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
        assert value in ["t1","t2","t3","f1","f2","f3","r"], "For P6, Atoms can only be over the available strings"
        self.value = value
    def __repr__(self):
        return self.value
    def atoms_used(self):
        return set([self.value])
    def evaluate(self, assignment):
        return self.value in assignment

class Verum(Formula):
    def __repr__(self):
        return "verum"
    def atoms_used(self):
        return set()
    def evaluate(self, assignment):
        return True

class Falsum(Formula):
    def __repr__(self):
        return "falsum"
    def atoms_used(self):
        return set()
    def evaluate(self, assignment):
        return False
