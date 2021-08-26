import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.DataProviderUtils;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static threadlocals.UserManager.getId;
import static threadlocals.UserManager.setId;
import static utils.HttpMethodUtils.delete;
import static utils.HttpMethodUtils.post;
import static utils.ObjectMapperUtils.getUser;


public class UserTests extends BaseTest{

    @Test (dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class, groups = {"delete_user_after"})
    public void testPostUser(Map<String,String> user) {
        Response response = post(getUser(user));

        assertEquals(response.jsonPath().get("name"), user.get("name"));
        assertEquals(response.jsonPath().get("job"), user.get("job"));
        assertEquals(response.getStatusCode(), Integer.parseInt(user.get("expected")));

        setId(response.jsonPath().get("id"));
    }

    @Test (groups = {"add_user_before"})
    public void testDeleteUser() {
        delete(getId())
                .then()
                .assertThat()
                .statusCode(204);
    }
}
