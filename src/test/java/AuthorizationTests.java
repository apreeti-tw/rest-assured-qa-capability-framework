import base.AuthBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.DataProviderUtils;

import java.util.Map;

public class AuthorizationTests extends AuthBaseTest {

    @Test(dataProvider = "AuthContainer", dataProviderClass = DataProviderUtils.class)
    public void testAuthWithClientCredentials(Map<String,String> auth) {
        Response response = newRequestSpec.post("/api/"+auth.get("user_id")+"/barn-unlock");
        System.out.println(response.jsonPath().getString("message"));
    }
}
