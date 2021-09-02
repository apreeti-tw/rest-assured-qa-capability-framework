package specifications;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.AuthorizationUtils;
import utils.PropertiesUtils;

import java.util.Map;

public class RequestSpecBuilder_New {

    private RequestSpecBuilder_New(){}

    private static final RequestSpecification baseRequestSpecification = RestAssured
            .given()
            .baseUri(PropertiesUtils.getProperty("base_url"))
            .contentType(ContentType.JSON)
            .urlEncodingEnabled(false);

    public static RequestSpecification getRequestSpec() {
        return baseRequestSpecification;
    }

    public static RequestSpecification getAuth(RequestSpecification reqSpec, Map<String, String> parameters){
        if (parameters.get("setAuth").equalsIgnoreCase("Y")) {
            if (parameters.get("authType").equalsIgnoreCase("oauth2CC")) {
                reqSpec = reqSpec.auth().oauth2(AuthorizationUtils.getAuthToken(parameters.get("authParams").split(";")));
            }
        }
        return reqSpec;
    }

    public static RequestSpecification getPathParams(RequestSpecification reqSpec, Map<String, String> parameters) {
        if (parameters.get("setPathParams").equalsIgnoreCase("Y")) {
            for (String param:parameters.get("pathParams").split(";")) {
                reqSpec = reqSpec.pathParams(param.split(":")[0], param.split(":")[1]);
            }
        }
        return reqSpec;
    }
}
