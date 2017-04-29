# RestAssuredExample

## Instructions:
This project was developed using intellij and maven, on MAC
- To run the project unzip the file into a directory
- Navigate to to the directory using terminal and run the following command
"mvn package -U"

## Test Scenarios:
General:
Each test will verify the response code and JSON schema
testEndpointIsAvailableWithNoInputs:
- End point returns is available
testInvalidInputString:
- End point returns a error, when no arguments are given
testValidZeroInput:
- End point returns 0 for a 0 input
testSimpleSubtraction:
- Subtracts one number form another
testSimpleAddition:
- Adds one number of another
testSimpleMultiplication:
- Multiples one number to another
testSimpleDivide:
- Divides one number form another
NOTE: I could not get this test to work, I assume as I've not tested end points before that I am passing in a invalid argument for "divide"
testNoCalculation:
- If no modifier is passed in, the first number in the array is returned
NOTE: I assumed the result based on what the end point does now, but I would talk to the project owner to clarify the expected behaviour
testThirdArgumentIsIgnored:
- Confirm only first 2 numbers are used, again behaviour would have to confirmed with product owner
NOTE: If I had more time I would repeat this test would more arguments and different calculations
testFractionInputsOnFirstNumber:
- Tests that a 500 error is returned if first number is a fraction
testFractionInputsOnLastNumber:
- Tests that a 500 error is returned if last number is a fraction
NOTE: This tests fails, as the behaviour is different ot other fractions, would need to confirm with product owner
testComplexEquationsAreNotSupported:
- Confirms complex calculations are not supports

Missing Tests:
One: Test maths formula's with more than one calculation i.e. "* (− 5 6) 7", assuming it is supported or verify if it is not
Two: If I had more time I would repeat "testThirdArgumentIsIgnored" with more arguments and different calculations

Failing Tests:
Currently 2 tests fail, as I am unsure as to the expected acceptance criteria of the API, these will need to be updated