import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import org.junit.Test;
import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;

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
        ValidatableResponse json;

        Response response = given().
                when().
                contentType("application/json").
                body("{ \"postcodes\" : [\"NW2 7DW\",\"NW10 2AP\"] }").
                post(url).
                then().
                contentType(ContentType.JSON).
                statusCode(200).extract().response();

        json = response.then().statusCode(200).log().ifError();
        JsonPath jsonPath = new JsonPath(json.extract().asString());
        String pathString = jsonPath.getString("result.result[0].postcode");

        System.out.println(response.toString());
        ArrayList j = response.body().jsonPath().get("result.result[0].postcode");
        System.out.println(j.size());

    }
}
