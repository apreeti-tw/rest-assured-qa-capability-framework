import constants.FilePaths;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pojo.User;
import specifications.RequestSpecBuilder;
import threadlocals.SpecManager;
import utils.HttpMethodUtils;

import java.io.IOException;

import static threadlocals.SpecManager.setRequestSpec;
import static threadlocals.UserManager.*;
import static utils.ObjectMapperUtils.getObjectFromJSON;


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
        Response response = HttpMethodUtils.post(getObjectFromJSON(FilePaths.getUsersJsonFilePath(), User.class));
        setId(response.jsonPath().get("id"));
    }

    @AfterMethod
    protected void tearDown(){
        SpecManager.unload();
    }
}
