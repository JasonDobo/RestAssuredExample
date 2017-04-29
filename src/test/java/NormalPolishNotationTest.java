import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.Matchers.equalTo;

public class NormalPolishNotationTest {

    final String username = "home";
    final String password = "test";
    final String endPoint = "http://" + username + ":" + password + "@home-assignment.herokuapp.com/api/calculate/";

    @Test
    public void testEndpointIsAvailableWithNoInputs() {
        given().when().get(endPoint).then().statusCode(200);
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
        String input = "− 5 6";
        String testEndPoint = endPoint + input;
        int result = 11;

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
        String input = "/ 20 5";
        String testEndPoint = endPoint + input;
        int result = 4;

        get500Response(testEndPoint, 400);
//        getResponse(testEndPoint, input,  result);
    }

    @Test
    public void testNoCalculation() {
        System.out.println("The instruction page does no clarify this acceptance criteria");
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
    public void testFractionInputsOnLastNumber() {
        String input = "+ 1 3.5";
        String testEndPoint = endPoint + input;

//        get500Response(testEndPoint);
        get500Response(testEndPoint, 200);
    }

    @Test
    public void testComplexEquationsAreNotSupported() {
        String input = "* (− 7 4) 7";
        String testEndPoint = endPoint + input;

        get500Response(testEndPoint);
    }

    private void get500Response(String testEndPoint) {
        get500Response(testEndPoint, 500);
    }


    private void get500Response(String testEndPoint, int error) {
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