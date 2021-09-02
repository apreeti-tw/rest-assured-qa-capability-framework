import base.BarnBaseTest;
import constants.Messages;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static enums.EndPoints.UNLOCK_BARN_REQUEST;

public class BarnFarmTests extends BarnBaseTest {

    @Test
    public void testBarnUnlockWithClientCredentials() {
        Response response = newRequestSpec.post(UNLOCK_BARN_REQUEST.getEndPoint());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), Messages.getBarnUnlockMessage());
    }
}
