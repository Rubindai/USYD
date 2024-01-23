HOME=/home/test/elaine
export HOME
USER=elaine
export USER
python3 jafr.py passwd<test8_elaineadd/test.in>test8_elaineadd/test.actual
diff test8_elaineadd/test.out test8_elaineadd/test.actual
