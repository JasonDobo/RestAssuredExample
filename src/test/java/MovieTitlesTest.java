import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static com.jayway.restassured.RestAssured.when;



public class MovieTitlesTest {

    @Test
    public void testMovieTitles() {
        Response response;
        ValidatableResponse json;
//        String movieTitles = null;
        ArrayList<String> movieTitles = new ArrayList<>();

        response = when().get("https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman");
        json = response.then().statusCode(200).log().all();

        String jsonString = json.extract().asString();
        JsonPath jsonPath = new JsonPath(jsonString);
        movieTitles = jsonPath.get("data.Title");


    }

    public static ArrayList<String> getMovieTitles() {
        Response response;
        ValidatableResponse json;
        ArrayList<String> movieTitles = new ArrayList<>();
        response = when().get("https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman");
        json = response.then().statusCode(200).log().all();
        try {
            String jsonString = json.extract().asString();
            JsonPath jsonpath = new JsonPath(jsonString);
            movieTitles = jsonpath.get("data.Title");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieTitles;
    }

    public static void main(String[] args) {
        ArrayList<String> movieTitles1 = new ArrayList<>();
        movieTitles1.addAll(getMovieTitles());
        Collections.sort(movieTitles1);
        System.out.println(movieTitles1);
    }

}
