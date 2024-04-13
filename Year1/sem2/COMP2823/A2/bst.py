'''
Your task is to complete the following functions which are marked by the TODO comment.
You are free to add properties and functions to the class as long as the given signatures remain identical.
Note: Please do not modify any existing function signatures, as it will impact your test results
'''


from os import defpath


class Student:
    """Represents a node of a binary tree."""
    """These are the defined properties as described above, feel free to add more if you wish!"""
    """However do not mmodify nor delete the current properties."""

    def __init__(self, student_id, name, GPA, depth=0) -> None:
        self.left = None
        self.right = None
        self.parent = None
        self.student_id = student_id
        self.name = name
        self.GPA = GPA
        self.depth = depth


class BSTree:

    """
    Implements an unbalanced Binary Search Tree.
    """

    def __init__(self, *args) -> None:
        self.Root = None

    """-------------------------------PLEASE DO NOT MODIFY THE ABOVE (except you could add more properties in Student class) -------------------------------"""

    def insert(self, student_id, name, GPA, node=None) -> None:
        """
        Inserts a new Student into the tree.
        """
        # TODO: Implement the method

        student_obj = Student(student_id, name, GPA)
        if (self.Root == None):
            self.Root = student_obj
        if (node == None):
            node = self.Root
            global depth
            depth = 0

        if (student_obj.student_id < node.student_id):
            if (node.left == None):
                depth += 1
                student_obj.depth = depth
                node.left = student_obj

                student_obj.parent = node

            else:
                node = node.left
                # print(node.depth)
                depth += 1
                return (self.insert(student_obj.student_id, student_obj.name, student_obj.GPA, node))
        elif (student_obj.student_id > node.student_id):
            if (node.right == None):
                depth += 1
                student_obj.depth = depth
                node.right = student_obj

                student_obj.parent = node
            else:
                node = node.right
                depth += 1
                return (self.insert(student_obj.student_id, student_obj.name, student_obj.GPA, node))

    def search(self, student_id) -> Student:
        """
        Searches for a student by student_id.
        """
        # TODO: Implement the method
        node = self.Root
        while (True):
            while (student_id < node.student_id):
                node = node.left
                if (node == None):
                    print("no record")
                    return

            while (student_id > node.student_id):
                node = node.right
                if (node == None):
                    print("no record")
                    return
            if (node.student_id == student_id):
                return node

    def delete(self, student_id) -> None:
        """
        Deletes a student from the tree by student_id.
        """
        # TODO: Implement the method
        student_obj = self.search(student_id)
        if (student_obj != None):

            if (student_obj.left == None and student_obj.right == None):  # case1
                if (student_obj == self.Root):
                    self.Root = None
                    return
                if (student_obj.parent.left == student_obj):
                    student_obj.parent.left = None
                    student_obj.parent = None
                elif (student_obj.parent.right == student_obj):
                    student_obj.parent.right = None
                    student_obj.parent = None

            elif (student_obj.left == None or student_obj.right == None):  # case2
                if (student_obj.left == None):
                    if (student_obj.parent.left == student_obj):

                        student_obj.parent.left = student_obj.right
                        student_obj.right.parent = student_obj.parent
                    elif (student_obj.parent.right == student_obj):
                        student_obj.parent.right = student_obj.right
                        student_obj.right.parent = student_obj.parent

                elif (student_obj.right == None):
                    if (student_obj.parent.left == student_obj):
                        student_obj.parent.left = student_obj.left
                        student_obj.left.parent = student_obj.parent
                    elif (student_obj.parent.right == student_obj):
                        student_obj.parent.right = student_obj.left
                        student_obj.left.parent = student_obj.parent

            elif (student_obj.left != None and student_obj.right != None):
                ls = self.inorder()  # case3
                i = 0
                while (i < len(ls)):
                    if (ls[i].student_id == student_id):
                        target = ls[i+1]
                    i += 1
                # print(target.name)

                student_obj.name = target.name
                student_obj.GPA = target.GPA
                student_obj.student_id = target.student_id

                # target.parent=student_obj.parent
                # target.left=student_obj.left
                # target.right=student_obj.right

                if (target.left == None and target.right == None):  # case1
                    if (target == self.Root):
                        self.Root = None
                        return
                    if (target.parent.left == target):
                        target.parent.left = None
                        target.parent = None
                    elif (target.parent.right == target):
                        target.parent.right = None
                        target.parent = None

                elif (target.left == None or target.right == None):  # case2
                    if (target.left == None):
                        if (target.parent.left == target):

                            target.parent.left = target.right
                            target.right.parent = target.parent
                        elif (target.parent.right == target):
                            target.parent.right = target.right
                            target.right.parent = target.parent

                    elif (target.right == None):
                        if (target.parent.left == target):
                            target.parent.left = target.left
                            target.left.parent = target.parent
                        elif (target.parent.right == target):
                            target.parent.right = target.left
                            target.left.parent = target.parent

    def update_gpa(self, student_id, new_gpa) -> None:
        """
        Updates the GPA of a student.
        """
        # TODO: Implement the method
        student_obj = self.search(student_id)
        if (student_obj != None):
            student_obj.GPA = new_gpa

    def update_name(self, student_id, new_name) -> None:
        """
        Updates the name of a student.
        """
        # TODO: Implement the method
        student_obj = self.search(student_id)
        if (student_obj != None):
            student_obj.name = new_name

    def update_student_id(self, old_id, new_id) -> None:
        """
        Updates the student ID. This requires special handling to maintain tree structure.
        """
        # TODO: Implement the method
        student_obj = self.search(old_id)
        if (student_obj != None):
            self.delete(student_obj)
            self.insert(new_id)

    def generate_report(self, student_id) -> str:
        """
        Generates a full report for a student.
        In the format of: Student ID: {student_id}, Name: {student.name}, GPA: {student.GPA}
        Otherwsie, print: No student found with ID {student_id}
        """
        # TODO: Implement the method
        student_obj = self.search(student_id)
        if (student_obj != None):
            return (f"Student ID: {student_id}, Name: {student_obj.name}, GPA: {student_obj.GPA}")
        else:
            return (f"No student found with ID {student_id}")

    def find_max_gpa(self) -> float:
        """
        Finds the maximum GPA in the tree.
        """
        # TODO: Implement the method
        ls = self.inorder()
        ls_new = []
        for i in ls:
            ls_new.append(i.GPA)

        ls_new.sort()

        return ls_new[-1]

    def find_min_gpa(self) -> float:
        """
        Finds the minimum GPA in the tree.
        """
        # TODO: Implement the method
        ls = self.inorder()
        ls_new = []
        for i in ls:
            ls_new.append(i.GPA)

        ls_new.sort()

        return ls_new[0]

    def levelorder(self, level=None) -> list:
        """
        Performs a level order traversal of the tree. If level is specified, returns all nodes at that level.
        """
        if not self.Root:
            return []

        queue = [(self.Root, 0)]
        current_level_nodes = []
        all_nodes = []

        while queue:
            node, curr_level = queue.pop(0)

            if (level != None):
                if curr_level == level:
                    current_level_nodes.append(node)
                if curr_level > level:
                    return current_level_nodes
            else:
                all_nodes.append(node)

            if node.left:
                queue.append((node.left, curr_level + 1))
            if node.right:
                queue.append((node.right, curr_level + 1))

        if level is not None:
            return current_level_nodes
        else:
            return all_nodes

    def inorder(self, n=0, ls=[], node=None) -> list:
        """
        Performs an in-order traversal of the tree.
        """
        # TODO: Implement the method
        if (n == 0):
            node = self.Root
            ls = []

        if (node.left != None):
            self.inorder(1, ls, node.left)
        ls.append(node)
        if (node.right != None):
            self.inorder(1, ls, node.right)

        return ls

    def is_valid(self) -> bool:
        """
        Checks if the tree is a valid Binary Search Tree. Return True if it is a valid BST, False or raise Exception otherwise.
        """
        # TODO: Implement the method
        ls = self.inorder()
        i = 0
        while i < len(ls)-1:
            if (ls[i].student_id > ls[i+1].student_id):
                return False
            i += 1
        return True


# a = BSTree()
# a.insert(3, "Alice", 3.5)
# a.insert(1, "Alice", 3.5)
# a.insert(2, "Bob", 3.7)
# a.insert(4, "Daisy", 3.2)
# a.insert(6, "Frank", 2.5)
# a.insert(10, "Chad", 2)
# a.insert(8, "Dalton", 1.5)
# a.insert(7, "Lex", 3.2)
# a.insert(11, "Roy", 3.1)
# a.delete(4)
# b = a.levelorder()
# for i in b:
#     print(i.student_id)
# # b=a.inorder()
# # for i in b:
# #     print(i.student_id)
# #     print(i.name)
# # print("")
# a.insert(8, "Bob", 3.7)
# # b=a.inorder()
# # for i in b:
# #     print(i.student_id)
# #     print(i.name)
# # print("")
# a.insert(12, "Charlie", 3.9)
# b=a.inorder()
# # for i in b:
# #     print(i.student_id)
# #     print(i.name)
# # print("")
