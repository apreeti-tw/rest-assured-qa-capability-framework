import enums.EndPoints;
import pojo.UserManager;
import utils.PropertiesUtils;
import specifications.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class PostUserTest {
    private RequestSpecification requestSpecification;
    String id = "";

    @BeforeTest
    public void setup() throws IOException {
        requestSpecification = RequestSpecBuilder.getRequestSpec();
    }

    @Test
    public void testPostUser() {
        Response response = requestSpecification
                                .body(new UserManager().getUser("Ayush", "leader"))
                                .post(EndPoints.POST_USER_REQUEST.getEndPoint());

        Assert.assertEquals(response.jsonPath().get("name"), "Ayush");
        Assert.assertEquals(response.jsonPath().get("job"), "leader");
        Assert.assertEquals(response.getStatusCode(), 201);
        id = response.jsonPath().get("id");
    }

    @AfterTest
    public void tearDown() {
        requestSpecification.pathParams("id", id).delete(EndPoints.DELETE_USER_REQUEST.getEndPoint()).then().assertThat().statusCode(204);
    }

}
