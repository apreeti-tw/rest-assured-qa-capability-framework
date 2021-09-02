package constants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertiesUtils;

public final class Specifications {
    private Specifications(){}

    private static final RequestSpecification baseRequestSpecification = RestAssured
        .given()
        .baseUri(PropertiesUtils.getProperty("base_url"))
        .contentType(ContentType.JSON)
        .urlEncodingEnabled(false);

    public static RequestSpecification getBaseRequestSpec(){
        return baseRequestSpecification;
    }
}
