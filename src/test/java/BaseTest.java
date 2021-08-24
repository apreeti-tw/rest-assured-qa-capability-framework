import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import specifications.RequestSpecBuilder;

import java.io.IOException;

public class BaseTest {
    protected RequestSpecification requestSpecification;

    @BeforeTest
    protected void setup() throws IOException {
        requestSpecification = RequestSpecBuilder.getRequestSpec();
    }
}
