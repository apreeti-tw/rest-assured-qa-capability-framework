package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static utils.HelperUtils.decodeBase64;

public class AuthorizationUtils {

    public static String getAuthToken(String base_url, String[] authFormParams) {
        RequestSpecification reqSpec = RestAssured.given().baseUri(base_url);
        for (String param:authFormParams) {
            reqSpec = reqSpec.formParam(param.split(":")[0], decodeBase64(param.split(":")[1]));
        }
        return reqSpec.post("/token").thenReturn().jsonPath().getString("access_token");
    }
}
