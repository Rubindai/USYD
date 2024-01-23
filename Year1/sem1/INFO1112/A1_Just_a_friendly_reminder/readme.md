# File tree hints
my note:
Hi, it's my first semester and I am doing 1110 at the same time, that's why the code seems so weird lol
I am improving it with functions but I don't think I can finish it before due.
I did learn a lot during this, anyway thanks for your patience to read through my code:)
##
This scaffold is meant to mimic a Unix filesystem with multiple users.

Inside the Edstem workspace, the parent directory of the scaffold is
your home directory and your username is user.

You can discover this for yourself by using the shell 
command `whoami` on Ed.

There are other users on the system, with their own home
directories inside the scaffold. You can discover
this inside the given `passwd` file.

Every user's home directory contains a hidden directory 
`.jafr`. Begin exploring the scaffold by trying to find these hidden
directories and their contents. Try the command `ls -a` inside each user's home 
directory.

Note that `ls -a` will also reveal `.bashrc`
in the scaffold directory.

Feel free to change any part of the scaffold file tree. This will not 
affect staff's testing methods.

# Testing locally

If you'd like to test locally (recommended), you should add a line
for your own system's user inside `passwd`. You could even copy
the relevant line from your `/etc/passwd` (replace the hashed password field for
good measure). You will also have to modify
the absolute paths to each user's home directory inside
the `passwd` file. You can do this safely without invalidating
staff's testing methods.

This assignment will be much easier if you work locally and upload to Ed 
periodically, rather than working inside Ed.
