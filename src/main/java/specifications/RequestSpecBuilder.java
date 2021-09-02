package specifications;

import io.restassured.specification.RequestSpecification;
import utils.AuthorizationUtils;

import java.util.Map;

import static constants.Specifications.getBaseRequestSpec;

public class RequestSpecBuilder {
    public RequestSpecification reqSpec;

    public RequestSpecBuilder(){
        reqSpec = getBaseRequestSpec();
    }

    public RequestSpecBuilder setBaseUri(String base_url) {
        reqSpec = reqSpec.baseUri(base_url);
        return this;
    }

    public RequestSpecBuilder getAuth(Map<String, String> parameters){
        if (parameters.get("setAuth").equalsIgnoreCase("Y")) {
            if (parameters.get("authType").equalsIgnoreCase("oauth2CC")) {
                reqSpec = reqSpec.auth().oauth2(AuthorizationUtils.getAuthToken(parameters.get("authParams").split(";")));
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

    public RequestSpecification build(){
        return reqSpec;
    }
}
