import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

public class PostUserTest {

    @Test
    public void testPostUser(){
        RestAssured.baseURI = "https://reqres.in/";

        String response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new File(System.getProperty("user.dir")+"/src/main/resources/UserDetails.json"))
                .when()
                .post("/api/users")
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
}
