HOME=/home/test/elaine
export HOME
USER=elaine
export USER
python3 jafr.py passwd<test9_elaineadd_invalid/test.in>test9_elaineadd_invalid/test.actual
diff test9_elaineadd_invalid/test.out test9_elaineadd_invalid/test.actual
