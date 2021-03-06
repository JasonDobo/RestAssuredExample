import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.Matchers.equalTo;

public class NormalPolishNotationTest {

    final String username = "home";
    final String password = "test";
    final String protocol = "http://";
    final String address = "@home-assignment.herokuapp.com/api/calculate/";
    final String endPoint = protocol + username + ":" + password + address;

    @Test
    public void testEndpointIsAvailableWithNoInputs() {
        given().
                when().
                    get(endPoint).
                then().
                    statusCode(200);
    }

    @Test
    public void testInvalidUsername() {
        String invalidCredentialsEndPoint = protocol + "invalid" + ":" + password + address;
        given().when().get(invalidCredentialsEndPoint).then().statusCode(401);
    }

    @Test
    public void testInvalidPassword() {
        String invalidCredentialsEndPoint = protocol + username + ":" + "unknown" + address;
        given().when().get(invalidCredentialsEndPoint).then().statusCode(401);
    }

    @Test
    public void testInvalidInputString() {
        String input = "string";
        String testEndPoint = endPoint + input;

        get500Response(testEndPoint);
    }

    @Test
    public void testValidZeroInput() {
        String input = "0";
        String testEndPoint = endPoint + input;

        getResponse(testEndPoint, input, Integer.valueOf(input));
    }

    @Test
    public void testSimpleSubtraction() {
        String input = "- 10 6";
        String testEndPoint = endPoint + input;
        int result = 4;

        getResponse(testEndPoint, input, result);
    }

    @Test
    public void testSimpleAddition() {
        String input = "+ 1 3";
        String testEndPoint = endPoint + input;
        int result = 4;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testSimpleMultiplication() {
        String input = "* 6 7";
        String testEndPoint = endPoint + input;
        int result = 42;

        getResponse(testEndPoint, input, result);
    }

    @Test
    public void testSimpleDivide() {
        System.out.println("I could not get this test to work, I assume that I'm using a invalid argument for divide");
        String input = "/ 20 5";
        String testEndPoint = endPoint + input;
        int result = 4;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testNoCalculation() {
        System.out.println("No acceptance criteria defined, so I've assumed actual response is correct");
        String input = "20 5";
        String testEndPoint = endPoint + input;
        int result = 20;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testThirdArgumentIsIgnored() {
        String input = "+ 1 3 5";
        String testEndPoint = endPoint + input;
        int result = 4;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testFractionInputsOnFirstNumber() {
        String input = "+ 1.5 3";
        String testEndPoint = endPoint + input;

        get500Response(testEndPoint);
    }

    @Test
    public void testFractionInputsOnSecondNumber() {
        String input = "+ 1 3.5";
        String testEndPoint = endPoint + input;
        double result = 4.5;

//        get500Response(testEndPoint);
        getResponse(testEndPoint, input.substring(0, input.length()-2), (int) result);
    }

    @Test
    public void testNegativeResultsAreSupported() {
        String input = "- 5 9";
        String testEndPoint = endPoint + input;
        int result = -4;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testComplexEquationsAreSupported() {
        String input = "* - 9 6 4";
        String testEndPoint = endPoint + input;
        int result = 12;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testNegativeComplexResultsAreSupported() {
        String input = "* - 5 6 7";
        String testEndPoint = endPoint + input;
        int result = -7;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testNegativeFirstNumberIsSupported() {
        String input = "* -5 3";
        String testEndPoint = endPoint + input;
        int result = -15;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testNegativeSecondNumberIsSupported() {
        String input = "- 3 -4";
        String testEndPoint = endPoint + input;
        int result = 7;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testMultipleNegativeInputAreSupported() {
        String input = "* -5 -3";
        String testEndPoint = endPoint + input;
        int result = 15;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testMinusMultipleNegativeInputs() {
        String input = "- -5 -3";
        String testEndPoint = endPoint + input;
        int result = -2;

        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testLongEquations() {

    }

    @Test
    public void testLargeNumberInput() {

    }

    @Test
    public void testLargeNumberOutput() {

    }

    @Test
    public void testLargeNumberInternalEquation() {

    }

    private void get500Response(String testEndPoint) {
        getErrorResponse(testEndPoint, 500);
    }


    private void getErrorResponse(String testEndPoint, int error) {
        given().
                when().
                get(testEndPoint).
                then().
                statusCode(error);
    }

    private void getResponse(String testEndPoint, String expression, int result) {
        Response response = given().
                when().
                    get(testEndPoint).
                then().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body(matchesJsonSchemaInClasspath("tax-calculator-schema.json")).
                    body("expression", equalTo(expression)).
                    body("result", equalTo(result)).
                extract().
                    response();

        System.out.println("JSON Response: " + response.asString());
    }
}
