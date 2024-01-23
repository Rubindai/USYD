HOME=/home/test
export HOME
python3 jafr.py passwd<test1_userdisplay/test.in>test1_userdisplay/test.actual
diff test1_userdisplay/test.out test1_userdisplay/test.actual

