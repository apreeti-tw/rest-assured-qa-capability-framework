package Utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

public class RequestSpecBuilder {

    private RequestSpecBuilder(){}

    public static RequestSpecification getRequestSpec() throws IOException {
        return RestAssured
                .given()
                .baseUri(PropertiesUtils.getProperty("base_url"))
                .header("Content-Type", "application/json")
                .urlEncodingEnabled(false);
    }
}
