package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class AuthorizationUtils {

    public static String getAuthToken(String[] authFormParams) {
        RequestSpecification reqSpec = RestAssured.given().baseUri(PropertiesUtils.getProperty("base_url"));
        for (String param:authFormParams) {
            reqSpec = reqSpec.formParam(param.split(":")[0], param.split(":")[1]);
        }
        return reqSpec.post("/token").thenReturn().jsonPath().getString("access_token");
    }
}