import base.UserBaseTest;
import builders.UserBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utils.DataProviderUtils;

import java.util.Map;

import static enums.EndPoints.DELETE_USER_REQUEST;
import static enums.EndPoints.POST_USER_REQUEST;
import static org.testng.Assert.assertEquals;


public class UserTests extends UserBaseTest {

    @Test (dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class)
    public void testPostUser(Map<String,String> user, ITestContext context) {
        Response response = RestAssured.given(requestSpecification)
                .body(new UserBuilder(user).setUserData())
                .post(POST_USER_REQUEST.getEndPoint());

        assertEquals(response.getStatusCode(), Integer.parseInt(user.get("expected")));
        assertEquals(response.jsonPath().get("name"), user.get("name"));
        assertEquals(response.jsonPath().get("job"), user.get("job"));

        context.setAttribute("user_id", response.jsonPath().get("id"));
    }

    @Test (dataProvider = "DataContainer", dataProviderClass = DataProviderUtils.class)
    public void testDeleteUser(Map<String,String> user, ITestContext context) {
        RestAssured.given(requestSpecification)
                .pathParams("user_id", context.getAttribute("user_id"))
                .delete(DELETE_USER_REQUEST.getEndPoint())
                .then()
                .statusCode(204);
    }
}
