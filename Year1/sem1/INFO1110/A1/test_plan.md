# Test Case Designs
Complete the given tables with details of your test case design for each question type.
State the values to initalize appropriate `Question` objects required for the test case.

Column descriptions:
* Test ID - Test case identification number
* Description - Type of testcase and brief explanation of test case details
* Inputs - Arguments into the method
* Expected Output - Return values of the method
* Status - pass/fail 

Table 1: Summary of test cases for method `mark_response` for question type `short`
| Test ID                   | Description                                                 | Inputs | Expected Output | Status |
|---------------------------|-------------------------------------------------------------|--------|-----------------|--------|
| 1.test_short_valid_mark   | Positive: type short question with valid correct user input | 'Bob'  | 2               | pass   |
| 2.test_short_valid_nomark | Positive: type short question with valid wrong user input   | 'kimm' | 0               | pass   |
| 3.test_short_invalid      | Negative: type short question with invalid user input       | 122    | 0               | pass   |

Table 2: Summary of test cases for method `mark_response` for question type `single`

| Test ID                    | Description                                                  | Inputs | Expected Output | Status |
|----------------------------|--------------------------------------------------------------|--------|-----------------|--------|
| 1.test_single_valid_mark   | Positive: type single question with valid correct user input | 'A'    | 2               | pass   |
| 2.test_single_valid_nomark | Positive: type single question with valid wrong user input   | 'A, B' | 0               | pass   |
| 3.test_single_invalid      | Negative: type single question with invalid user input       | 122    | 0               | pass   |

Table 3: Summary of test cases for method `mark_response` for question type `multiple`

| Test ID                      | Description                                                    | Inputs    | Expected Output | Status |
|------------------------------|----------------------------------------------------------------|-----------|-----------------|--------|
| 1.test_multiple_valid_mark   | Positive: type multiple question with valid correct user input | 'B, C, D' | 2               | pass   |
| 2.test_multiple_valid_nomark | Positive: type multiple question with valid wrong user input   | 'A, B'    | 0.67            | pass   |
| 3.test_multiple_invalid      | Negative: type multiple question with invalid user input       | 122       | 0               | pass   |
# 
