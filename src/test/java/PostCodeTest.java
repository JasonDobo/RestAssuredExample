import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.Matchers.equalTo;

public class PostCodeTest {

    String url = "https://api.postcodes.io/postcodes/";

    @Test
    public void testEndpointIsAvailableWithNoInputs() {
        given().
                when().
                post(url).
                then().
                statusCode(500);
    }

    @Test
    public void testSinglePostCode() {
        Response body = given().
                when().
                contentType("application/json").
                body("{ \"postcodes\" : [\"NW2 7DW\",\"NW10 2AP\"] }").
                post(url).
                then().
                contentType(ContentType.JSON).
                statusCode(200).extract().response();

        System.out.println(body.toString());
        ArrayList j = body.body().jsonPath().get("result.result[0].postcode");
        System.out.println(j.size());

    }
}
