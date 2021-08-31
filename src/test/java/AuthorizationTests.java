import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import specifications.RequestSpecBuilder;
import utils.AuthorizationUtils;
import utils.DataProviderUtils;

import java.util.Arrays;
import java.util.Map;

public class AuthorizationTests {
    private RequestSpecification newRequestSpec = RequestSpecBuilder.getRequestSpec();

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

    @Test(dataProvider = "AuthContainer", dataProviderClass = DataProviderUtils.class)
    public void testAuthWithClientCredentials(Map<String,String> auth) {
        Response response = newRequestSpec.post("/api/"+auth.get("user_id")+"/barn-unlock");
        System.out.println(response.jsonPath().getString("message"));
    }
}
