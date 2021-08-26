import enums.EndPoints;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import specifications.RequestSpecBuilder;

import java.io.File;
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
        getRequestSpec().pathParams("id", getId())
                .delete(EndPoints.DELETE_USER_REQUEST.getEndPoint())
                .then().assertThat().statusCode(204);
        unload();
    }

    @BeforeMethod(dependsOnGroups = {"add_user_before"})
    protected void addUserBeforeTest() throws IOException {
        setRequestSpec(RequestSpecBuilder.getRequestSpec());

        Response response = getRequestSpec()
                .body(new File(System.getProperty("user.dir")+"/src/main/resources/UserDetails.json"))
                .post(EndPoints.POST_USER_REQUEST.getEndPoint());
        setId(response.jsonPath().get("id"));
    }
}
