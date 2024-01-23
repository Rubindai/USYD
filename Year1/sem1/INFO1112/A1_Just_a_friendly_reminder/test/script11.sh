HOME=/home/test/rubin
export HOME
USER=rubin
export USER
python3 jafr.py test11_rubin_nojson/test.in>test11_rubin_nojson/test.actual 2>test11_rubin_nojson/test.actual
diff test11_rubin_nojson/test.out test11_rubin_nojson/test.actual
