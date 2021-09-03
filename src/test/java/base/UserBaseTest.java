package base;

import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import specifications.RequestSpecBuilder;

public class UserBaseTest {
    protected RequestSpecification requestSpecification;

    @BeforeMethod
    public void beforeMethod(ITestContext context){
        requestSpecification = new RequestSpecBuilder().setBaseUri("https://reqres.in").build();
    }
}
