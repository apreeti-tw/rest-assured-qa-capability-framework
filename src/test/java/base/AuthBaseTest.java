package base;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import specifications.RequestSpecBuilder;
import utils.AuthorizationUtils;

import java.util.Arrays;
import java.util.Map;

public class AuthBaseTest {
    protected RequestSpecification newRequestSpec = RequestSpecBuilder.getRequestSpec();

    @BeforeMethod
    public void setTestLevelRequestSpec(Object[] params){
        Arrays.stream(params).forEach(obj -> {
            Map<String, String> paramMap = (Map<String, String>) obj;
            if (paramMap.get("authRequired").equalsIgnoreCase("Y")) {
                if (paramMap.get("authType").equalsIgnoreCase("oauth2CC")) {
                    newRequestSpec = newRequestSpec.auth().oauth2(AuthorizationUtils.getAuthToken(paramMap.get("authParams").split(";")));
                }
            }
        });
    }
}
