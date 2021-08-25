import enums.EndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.UserManager;
import utils.DataProviderUtils;

import java.util.Map;

import static pojo.DataManager.setId;

public class PostUserTest extends BaseTest{

    @Test (dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class)
    public void testPostUser(Map<String,String> user) {
        Response response = requestSpecification
                                .body(UserManager.getUser(user))
                                .post(EndPoints.POST_USER_REQUEST.getEndPoint());

        Assert.assertEquals(response.jsonPath().get("name"), "Ayush");
        Assert.assertEquals(response.jsonPath().get("job"), "Leader");
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(user.get("expected")));

        setId(response.jsonPath().get("id"));
    }



}
