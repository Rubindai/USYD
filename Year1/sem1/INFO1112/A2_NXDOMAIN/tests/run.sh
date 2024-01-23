coverage erase

coverage run --append /home/server.py&>/home/tests/test1_invalid_arg/test.actual #test1
coverage report -m
coverage html --directory /home/tests/test1_invalid_arg
diff /home/tests/test1_invalid_arg/test.actual /home/tests/test1_invalid_arg/test.out

coverage run --append /home/server.py /home/tests/test2_invalid_conf1/invalid.conf &>/home/tests/test2_invalid_conf1/test.actual #test2
coverage report -m
coverage html --directory /home/tests/test2_invalid_conf1
diff /home/tests/test2_invalid_conf1/test.actual /home/tests/test2_invalid_conf1/test.out

coverage run --append /home/server.py /home/tests/test3_invalid_conf2/invalid.conf &>/home/tests/test3_invalid_conf2/test.actual #test3
coverage report -m
coverage html --directory /home/tests/test3_invalid_conf2
diff /home/tests/test3_invalid_conf2/test.actual /home/tests/test3_invalid_conf2/test.out

coverage run --append /home/server.py /home/tests/test4_invalid_conf3/invalid.conf &>/home/tests/test4_invalid_conf3/test.actual #test4
coverage report -m
coverage html --directory /home/tests/test4_invalid_conf3
diff /home/tests/test4_invalid_conf3/test.actual /home/tests/test4_invalid_conf3/test.out

coverage run --append /home/server.py /home/tests/test5_invalid_conf4/invalid.conf &>/home/tests/test5_invalid_conf4/test.actual #test5
coverage report -m
coverage html --directory /home/tests/test5_invalid_conf4
diff /home/tests/test5_invalid_conf4/test.actual /home/tests/test5_invalid_conf4/test.out

coverage run --append /home/server.py /home/tests/test6_invalid_conf5/invalid.conf &>/home/tests/test6_invalid_conf5/test.actual #test6
coverage report -m
coverage html --directory /home/tests/test6_invalid_conf5
diff /home/tests/test6_invalid_conf5/test.actual /home/tests/test6_invalid_conf5/test.out

coverage run --append /home/server.py /home/tests/test7_invalid_con6/invalid.conf &>/home/tests/test7_invalid_con6/test.actual #test7
coverage report -m
coverage html --directory /home/tests/test7_invalid_con6
diff /home/tests/test7_invalid_con6/test.actual /home/tests/test7_invalid_con6/test.out

coverage run --append /home/server.py /home/tests/test8_valid_simple/valid.conf>/home/tests/test8_valid_simple/test.actual & #test8
sleep 1
python /home/tests/test8_valid_simple/dummy.py
sleep 1
coverage report -m
coverage html --directory /home/tests/test8_valid_simple
diff /home/tests/test8_valid_simple/test.actual /home/tests/test8_valid_simple/test.out

coverage run --append /home/server.py /home/tests/test9_valid_complex/valid.conf>/home/tests/test9_valid_complex/test.actual & #test9
sleep 1
python /home/tests/test9_valid_complex/dummy.py
sleep 1
coverage report -m
coverage html --directory /home/tests/test9_valid_complex
diff /home/tests/test9_valid_complex/test.actual /home/tests/test9_valid_complex/test.out

coverage run --append /home/server.py /home/tests/test10_invalid_conf7/invalid.conf &>/home/tests/test10_invalid_conf7/test.actual #test10
coverage report -m
coverage html --directory /home/tests/test10_invalid_conf7
diff /home/tests/test10_invalid_conf7/test.actual /home/tests/test10_invalid_conf7/test.out

coverage run --append /home/server.py /home/tests/test11_invalid_con8/invalid.conf &>/home/tests/test11_invalid_con8/test.actual #test11
coverage report -m
coverage html --directory  /home/tests/test11_invalid_con8
diff /home/tests/test11_invalid_con8/test.actual /home/tests/test11_invalid_con8/test.out

coverage run --append /home/server.py /home/tests/test12_invalid_con_edge/invalid.conf &>/home/tests/test12_invalid_con_edge/test.actual #test12
coverage report -m
coverage html --directory /home/tests/test12_invalid_con_edge
diff /home/tests/test12_invalid_con_edge/test.actual /home/tests/test12_invalid_con_edge/test.out
sleep 1

coverage run --append /home/server.py /home/tests/test13_valid_incomplete/valid.conf>/home/tests/test13_valid_incomplete/test.actual & #test13
sleep 1
python /home/tests/test13_valid_incomplete/dummy.py
sleep 1
coverage report -m
coverage html --directory /home/tests/test13_valid_incomplete
diff /home/tests/test13_valid_incomplete/test.actual /home/tests/test13_valid_incomplete/test.out



coverage run --append /home/server.py /home/tests/test14_valid_buffer/valid.conf>/home/tests/test14_valid_buffer/test.actual & #test14
sleep 1
python /home/tests/test14_valid_buffer/dummy.py
sleep 1
coverage report -m
coverage html --directory /home/tests/test14_valid_buffer
diff /home/tests/test14_valid_buffer/test.actual /home/tests/test14_valid_buffer/test.out
# python /home/tests/test1/dummy.py 
# sleep 2
# printf 'google.com\n' | ncat localhost 8880
# printf '!EXIT\n' | ncat localhost 8880
# sleep 2
# #

# coverage run --append /home/server.py /home/tests/sample.conf &
# sleep 2
# printf 'google\n' | ncat localhost 8880
# printf '!EXIT\n' | ncat localhost 8880
# sleep 2
# coverage report -m

# coverage run --append /home/server.py /home/tests/sample.con &
# # sleep 2
# # printf 'google\n' | ncat localhost 8880
# # printf '!EXIT\n' | ncat localhost 8880
# sleep 2
# coverage report -m
