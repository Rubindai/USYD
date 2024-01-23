#!/usr/bin/bash
cd /home/rubin/USYD/First_year/elec1601/robot
gcc wall.c formulas.c robot.c main.c -o main `sdl2-config --cflags --libs` -lm

./main
