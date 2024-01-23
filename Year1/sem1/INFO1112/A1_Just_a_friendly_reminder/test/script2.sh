HOME=/home/test
export HOME
python3 jafr.py passwd<test2_usercomplete/test.in>test2_usercomplete/test.actual
diff test2_usercomplete/test.out test2_usercomplete/test.actual
