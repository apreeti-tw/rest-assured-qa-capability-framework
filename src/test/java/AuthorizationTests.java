import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import utils.DataProviderUtils;

import java.util.Map;

public class AuthorizationTests {
    @Test(dataProvider = "AuthContainer", dataProviderClass = DataProviderUtils.class)
    public void testAuthWithClientCredentials(Map<String,String> auth) {
        String[] formParams = auth.get("formParam").split(";");
        Response response = RestAssured.given()
                .baseUri("http://coop.apps.symfonycasts.com/")
                .auth()
                .oauth2(generateAccessToken(formParams))
                .when()
                .post("/api/"+auth.get("user_id")+"/barn-unlock");
        System.out.println(response.jsonPath().getString("message"));
    }

    public static String generateAccessToken(String[] formParams){
        RequestSpecification reqSpec = RestAssured.given().baseUri("http://coop.apps.symfonycasts.com/");
        for (String param:formParams) {
            reqSpec = reqSpec.formParam(param.split(":")[0], param.split(":")[1]);
        }
        Response accessTokenResponse = reqSpec.post("/token");
        return accessTokenResponse.jsonPath().getString("access_token");
    }
}
