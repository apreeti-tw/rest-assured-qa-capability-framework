package base;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import specifications.RequestSpecBuilder;
import utils.AuthorizationUtils;

import java.util.Map;

public class BarnBaseTest {
    protected RequestSpecification newRequestSpec = RequestSpecBuilder.getRequestSpec();

    @BeforeMethod
    public void setTestLevelRequestSpec(Object[] params){
        if (((Map<String,String>) params[0]).get("setAuth").equalsIgnoreCase("Y")) {
            if (((Map<String,String>) params[0]).get("authType").equalsIgnoreCase("oauth2CC")) {
                newRequestSpec = newRequestSpec.auth().oauth2(AuthorizationUtils.getAuthToken(((Map<String,String>) params[0]).get("authParams").split(";")));
            }
        }
        if (((Map<String,String>) params[0]).get("setPathParams").equalsIgnoreCase("Y")) {
           newRequestSpec = RequestSpecBuilder.getPathParams(newRequestSpec,((Map<String,String>) params[0]).get("pathParams").split(";"));
        }
    }
}
