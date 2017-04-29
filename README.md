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
- End point returns a 500 error, when no arguments are given
testValidZeroInput:
- End point returns 0 for a 0 input
testSimpleSubtraction:
- Subtracts one number form another
testSimpleAddition:
- Adds one number to another
testSimpleMultiplication:
- Multiply one number to another
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
testFractionInputsOnSecondNumber:
- Tests that a 500 error is returned if last number is a fraction
NOTE: This and previous test are unclear, the API behaviour differently depending if the first or second number is a fraction.
When the first is a fraction it returns 500 and when the second one is a fraction it ignores the fraction
testNegativeResultsAreSupported:
- Confirm that the API will return a negative result to a simple calculation
testComplexEquationsAreSupported:
- Test that 2 modifiers can be used in a equation, in this case * and +
testNegativeComplexResultsAreSupported:
- Confirm that the API will return a negative result to a complex calculation
testNegativeFirstNumberIsSupported:
- Confirm the APi will accept a first number that is negative
testNegativeSecondNumberIsSupported:
- Confirm the APi will accept a second number that is negative
testMultipleNegativeInputsAreSupported:
- Confirm the APi will accept negative numbers for first and second parameter
testMinusMultipleNegativeInputs:
- Test that 2 negatives numbers can be subtracted

Confirms complex calculations with 2 modifiers is supported
One: Test maths formula's with more than one calculation i.e. "* − 7 4 3", assuming it is supported or verify if it is not

Missing Tests:
If I had more time I would repeat "testThirdArgumentIsIgnored" with more arguments and different calculations

Failing Tests:
Currently 2 tests fail, as I am unsure as to the expected acceptance criteria of the API, these will need to be updated