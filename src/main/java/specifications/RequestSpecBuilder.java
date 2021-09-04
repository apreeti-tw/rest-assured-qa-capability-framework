package specifications;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static utils.AuthorizationUtils.getAuthToken;

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

    public RequestSpecBuilder getAuth(Map<String, String> parameters){
        if (parameters.get("setAuth").equalsIgnoreCase("Y")) {
            if (parameters.get("authType").equalsIgnoreCase("oauth2CC")) {
                reqSpec = reqSpec.auth().oauth2(getAuthToken(parameters.get("base_url"), parameters.get("authParams").split(";")));
            }
        }
        return this;
    }

    public RequestSpecBuilder getPathParams(Map<String, String> parameters) {
        if (parameters.get("setPathParams").equalsIgnoreCase("Y")) {
            for (String param:parameters.get("pathParams").split(";")) {
                reqSpec = reqSpec.pathParams(param.split(":")[0], param.split(":")[1]);
            }
        }
        return this;
    }

    public RequestSpecBuilder setBody(Object body) {
        reqSpec = reqSpec.body(body);
        return this;
    }

    public RequestSpecification build(){
        return reqSpec;
    }
}
