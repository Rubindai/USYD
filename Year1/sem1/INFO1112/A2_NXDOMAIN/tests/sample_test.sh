#!/bin/bash

# first install coverage.py:
# https://coverage.readthedocs.io/en/latest/install.html

# earse previous coverage
coverage erase
# start a hard-coded server in background by coverage
coverage run --append /home/rubin/USYD/First_year/info1112/assignment2/nxdomain/tests/sample_server.py /home/rubin/USYD/First_year/info1112/assignment2/nxdomain/tests/sample.conf &
# delay 2s to make sure the server is up and listening at port 1024
sleep 2
echo fake recursor sends EXIT
# terminate the server by sending EXIT command
printf '!EXIT\n' | nc localhost 1024
# delay 0.1s
sleep 0.1
# print the coverage report, expect 100% coverage rate
coverage report -m
# just output the coverage without testing the output
