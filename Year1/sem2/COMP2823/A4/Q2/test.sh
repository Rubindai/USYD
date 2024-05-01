#!/bin/bash

program="python3 a4.py"

# Find all files ending with ".in"
test_files=$(find . -type f -name "*.in")

# Iterate over each test file
for input_file in $test_files; do

    # Print test file name
    echo "Testing $input_file"

    # Generate output file name
    output_file="${input_file%.in}.out"
    
    # Run the program with input from the current test file
    output=$(cat "$input_file" | $program)
    
    # Read the expected output from the corresponding .out file
    expected_output=$(cat "$output_file")
    
    # Compare the program's output with the expected output
    if [ "$output" != "$expected_output" ]; then
        echo "Test failed for input file: $input_file"
        echo "Expected output:"
        echo "$expected_output"
        echo "Actual output:"
        echo "$output"
    else
        echo "Test passed!"
    fi

    echo "-----------------------------------------"
done
