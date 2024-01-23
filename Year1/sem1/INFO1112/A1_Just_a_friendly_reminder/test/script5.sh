HOME=/home/test
export HOME
python3 jafr.py passwd<test5_useradd_invalid/test.in>test5_useradd_invalid/test.actual
diff test5_useradd_invalid/test.out test5_useradd_invalid/test.actual
