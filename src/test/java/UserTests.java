import base.UserBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.DataProviderUtils;

import java.util.Map;

import static enums.EndPoints.DELETE_USER_REQUEST;
import static enums.EndPoints.POST_USER_REQUEST;
import static org.testng.Assert.assertEquals;
import static threadlocals.UserManager.setId;
import static utils.ObjectMapperUtils.getUser;


public class UserTests extends UserBaseTest {

    @Test (dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class)
    public void testPostUser(Map<String,String> user) {
        Response response = requestSpecification.body(getUser(user)).post(POST_USER_REQUEST.getEndPoint());

        assertEquals(response.jsonPath().get("name"), user.get("name"));
        assertEquals(response.jsonPath().get("job"), user.get("job"));
        assertEquals(response.getStatusCode(), Integer.parseInt(user.get("expected")));

        setId(response.jsonPath().get("id"));
    }

    @Test
    public void testDeleteUser() {
        requestSpecification
                .pathParams("id", "someid")
                .delete(DELETE_USER_REQUEST.getEndPoint())
                .then()
                .statusCode(204);
    }
}
