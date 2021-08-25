import enums.EndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.UserManager;
import utils.DataProviderUtils;

import java.util.Map;

import static pojo.DataManager.getId;
import static pojo.DataManager.setId;

public class PostUserTest extends BaseTest{

    @Test (dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class, groups = {"delete_user_after"})
    public void testPostUser(Map<String,String> user) {
        Response response = requestSpecification
                                .body(UserManager.getUser(user))
                                .post(EndPoints.POST_USER_REQUEST.getEndPoint());

        Assert.assertEquals(response.jsonPath().get("name"), user.get("name"));
        Assert.assertEquals(response.jsonPath().get("job"), user.get("job"));
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(user.get("expected")));

        setId(response.jsonPath().get("id"));
    }

    @Test (groups = {"add_user_before"})
    public void testDeleteUser() {
        Response response = requestSpecification
                .pathParams("id", getId())
                .delete(EndPoints.DELETE_USER_REQUEST.getEndPoint());

        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
