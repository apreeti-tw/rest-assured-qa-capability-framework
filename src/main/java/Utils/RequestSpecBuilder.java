package Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

public class RequestSpecBuilder {

    private RequestSpecBuilder(){}

    public static RequestSpecification getRequestSpec() throws IOException {
        return RestAssured
                .given()
                .baseUri(PropertiesUtils.getProperty("base_url"))
                .contentType(ContentType.JSON)
                .urlEncodingEnabled(false);
    }
}
