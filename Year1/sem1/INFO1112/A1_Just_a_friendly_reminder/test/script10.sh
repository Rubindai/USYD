HOME=/home/test/john
export HOME
USER=john
export USER
python3 jafr.py passwd<test10_john_nomeetings/test.in>test10_john_nomeetings/test.actual 2>test10_john_nomeetings/test.actual
diff test10_john_nomeetings/test.out test10_john_nomeetings/test.actual
