import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import threadlocals.UserManager;
import utils.DataProviderUtils;
import utils.HttpMethodUtils;

import java.util.Map;

import static threadlocals.UserManager.getId;
import static threadlocals.UserManager.setId;


public class PostUserTest extends BaseTest{

    @Test (dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class, groups = {"delete_user_after"})
    public void testPostUser(Map<String,String> user) {
        Response response = HttpMethodUtils.post(UserManager.getUser(user));

        Assert.assertEquals(response.jsonPath().get("name"), user.get("name"));
        Assert.assertEquals(response.jsonPath().get("job"), user.get("job"));
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(user.get("expected")));

        setId(response.jsonPath().get("id"));
    }

    @Test (groups = {"add_user_before"})
    public void testDeleteUser() {
        Response response = HttpMethodUtils.delete(getId());

        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
