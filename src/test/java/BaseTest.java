import constants.FilePaths;
import enums.EndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pojo.User;
import specifications.RequestSpecBuilder;
import utils.HttpMethodUtils;
import utils.JSONToObjectMapperUtils;

import java.io.IOException;

import static threadlocals.SpecManager.getRequestSpec;
import static threadlocals.SpecManager.setRequestSpec;
import static threadlocals.UserManager.*;


public class BaseTest {

    @BeforeMethod(dependsOnGroups = {"delete_user_after"})
    protected void setup() throws IOException {
        setRequestSpec(RequestSpecBuilder.getRequestSpec());
    }

    @AfterMethod(dependsOnGroups = {"delete_user_after"})
    public void deleteUserAfterTest() {
        HttpMethodUtils.delete(getId()).then().assertThat().statusCode(204);
        unload();
    }

    @BeforeMethod(dependsOnGroups = {"add_user_before"})
    protected void addUserBeforeTest() throws IOException {
        setRequestSpec(RequestSpecBuilder.getRequestSpec());

        Response response = getRequestSpec()
                .body(JSONToObjectMapperUtils.getObjectFromJSON(FilePaths.getUsersJsonFilePath(), User.class))
                .post(EndPoints.POST_USER_REQUEST.getEndPoint());

        setId(response.jsonPath().get("id"));
    }
}
