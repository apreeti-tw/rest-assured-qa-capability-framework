import enums.EndPoints;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pojo.DataManager;
import specifications.RequestSpecBuilder;

import java.io.File;
import java.io.IOException;

import static pojo.DataManager.getId;
import static pojo.DataManager.unload;

public class BaseTest {
    protected RequestSpecification requestSpecification;

    @BeforeMethod(dependsOnGroups = {"delete_user_after"})
    protected void setup() throws IOException {
        requestSpecification = RequestSpecBuilder.getRequestSpec();
    }

    @AfterMethod(dependsOnGroups = {"delete_user_after"})
    public void deleteUserAfterTest() {
        requestSpecification.pathParams("id", getId())
                .delete(EndPoints.DELETE_USER_REQUEST.getEndPoint())
                .then().assertThat().statusCode(204);
        unload();
    }

    @BeforeMethod(dependsOnGroups = {"add_user_before"})
    protected void addUserBeforeTest() throws IOException {
        requestSpecification = RequestSpecBuilder.getRequestSpec();
        Response response = requestSpecification
                .body(new File(System.getProperty("user.dir")+"/src/main/resources/UserDetails.json"))
                .post(EndPoints.POST_USER_REQUEST.getEndPoint());
        DataManager.setId(response.jsonPath().get("id"));
    }
}
