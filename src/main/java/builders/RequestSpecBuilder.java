package builders;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilder {
    public RequestSpecification reqSpec;

    public RequestSpecBuilder(){
        reqSpec = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .urlEncodingEnabled(false);
    }

    public RequestSpecBuilder setBaseUri(String base_url) {
        reqSpec = reqSpec.baseUri(base_url);
        return this;
    }

    public RequestSpecBuilder setBody(Object body) {
        reqSpec = reqSpec.body(body);
        return this;
    }

    public RequestSpecification build(){
        return reqSpec;
    }

    public RequestSpecBuilder setHeaders(String headers) {
        for (String header: headers.split(";")) {
            reqSpec = reqSpec.header(header.split(":")[0], header.split(":")[1]);
        }
        return this;
    }

    public RequestSpecBuilder setQueryParams(String queryParams) {
        for (String queryParam: queryParams.split(";")) {
            reqSpec = reqSpec.queryParams(queryParam.split(":")[0], queryParam.split(":")[1]);
        }
        return this;
    }
}
