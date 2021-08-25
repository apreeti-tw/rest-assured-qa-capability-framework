import enums.EndPoints;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import specifications.RequestSpecBuilder;

import java.io.IOException;

import static pojo.DataManager.getId;
import static pojo.DataManager.unload;

public class BaseTest {
    protected RequestSpecification requestSpecification;

    @BeforeTest
    protected void setup() throws IOException {
        requestSpecification = RequestSpecBuilder.getRequestSpec();
    }

    @AfterTest
    public void tearDown() {
        requestSpecification.pathParams("id", getId())
                .delete(EndPoints.DELETE_USER_REQUEST.getEndPoint())
                .then().assertThat().statusCode(204);
        unload();
    }
}
