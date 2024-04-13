import unittest
from bst import BSTree, Student


def assert_equal(got, expected, msg):
    """
    Simple assert helper
    """
    assert expected == got, "[{}] Expected: {}, got: {}".format(
        msg, expected, got)


class BSTreeTestCases(unittest.TestCase):
    """
    Test cases for the BSTree and Student classes.
    """

    def setUp(self):
        self.bst = BSTree()
        students = [
            (3, "Charlie", 3.9),
            (1, "Alice", 3.5),
            (2, "Bob", 3.7),
            (4, "Daisy", 3.2),
        ]
        for student_id, name, GPA in students:
            self.bst.insert(student_id, name, GPA)

    def test_level_order_magic(self):
        assert_equal([x.student_id for x in self.bst.levelorder()], [
                     3, 1, 4, 2], "Basic level order")
        students = [
            (6, "Frank", 2.5),
            (10, "Chad", 2),
            (8, "Dalton", 1.5),
            (7, "Lex", 3.2),
            (11, "Roy", 3.1)
        ]
        for student_id, name, GPA in students:
            self.bst.insert(student_id, name, GPA)
        assert_equal([x.student_id for x in self.bst.levelorder()], [
                     3, 1, 4, 2, 6, 10, 8, 11, 7], "Bigger order")
        self.bst.delete(4)
        assert_equal([x.student_id for x in self.bst.levelorder()], [
                     3, 1, 6, 2, 10, 8, 11, 7], "Remove one")
        self.bst.insert(5, "Bob", 2.4)
        assert_equal([x.student_id for x in self.bst.levelorder()], [
                     3, 1, 6, 2, 5, 10, 8, 11, 7], "Add one")
        assert_equal([x.student_id for x in self.bst.levelorder(2)], [
                     2, 5, 10], "Add one")
        for node_id in [x.student_id for x in self.bst.levelorder()]:
            self.bst.delete(node_id)
        assert_equal(
            [x.student_id for x in self.bst.levelorder()], [], "Empty")
