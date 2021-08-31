package specifications;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertiesUtils;

public class RequestSpecBuilder {

    private RequestSpecBuilder(){}

    private static final RequestSpecification baseRequestSpecification = RestAssured
            .given()
            .baseUri(PropertiesUtils.getProperty("base_url"))
            .contentType(ContentType.JSON)
            .urlEncodingEnabled(false);

    public static RequestSpecification getRequestSpec() {
        return baseRequestSpecification;
    }
}
