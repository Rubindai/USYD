HOME=/home/test
export HOME
python3 jafr.py passwd<test4_useradd/test.in>test4_useradd/test.actual
diff test4_useradd/test.out test4_useradd/test.actual
