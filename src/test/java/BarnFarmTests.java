import base.BarnBaseTest;
import constants.Messages;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DataProviderUtils;

import java.util.Map;

import static enums.EndPoints.UNLOCK_BARN_REQUEST;

public class BarnFarmTests extends BarnBaseTest {

    @Test (dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class)
    public void testBarnUnlockWithClientCredentials(Map<String,String> barnData) {
        Response response = RestAssured.given(requestSpecification).post(UNLOCK_BARN_REQUEST.getEndPoint());

        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(barnData.get("expected")));
        Assert.assertEquals(response.jsonPath().getString("message"), Messages.getBarnUnlockMessage());
    }
}
