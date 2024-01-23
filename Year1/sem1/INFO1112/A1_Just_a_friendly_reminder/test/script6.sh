HOME=/home/test
export HOME
python3 jafr.py passwd<test6_usergeneral/test.in>test6_usergeneral/test.actual
diff test6_usergeneral/test.out test6_usergeneral/test.actual
