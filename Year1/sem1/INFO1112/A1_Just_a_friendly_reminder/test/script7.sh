HOME=/home/test/elaine
export HOME
USER=elaine
export USER
python3 jafr.py passwd<test7_elaine_notask/test.in>test7_elaine_notask/test.actual
diff test7_elaine_notask/test.out test7_elaine_notask/test.actual
