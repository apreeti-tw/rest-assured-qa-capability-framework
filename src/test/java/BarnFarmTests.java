import base.BarnBaseTest;
import constants.Messages;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DataProviderUtils;

import java.util.Map;

public class BarnFarmTests extends BarnBaseTest {

    @Test(dataProvider = "BarnContainer", dataProviderClass = DataProviderUtils.class)
    public void testBarnUnlockWithClientCredentials(Map<String,String> auth) {
        Response response = newRequestSpec.post("/api/1711/barn-unlock").then().log().all().extract().response();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), Messages.getBarnUnlockMessage());
    }
}
