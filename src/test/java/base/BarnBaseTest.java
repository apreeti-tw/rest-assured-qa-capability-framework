package base;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import specifications.RequestSpecBuilder;

import java.util.Map;

import static specifications.RequestSpecBuilder.getAuth;
import static specifications.RequestSpecBuilder.getPathParams;

public class BarnBaseTest {
    protected RequestSpecification newRequestSpec = RequestSpecBuilder.getRequestSpec();

    @BeforeMethod
    public void setTestLevelRequestSpec(Object[] params){
        newRequestSpec = getAuth(newRequestSpec, (Map<String, String>) params[0]);
        newRequestSpec = getPathParams(newRequestSpec, (Map<String, String>) params[0]);
    }
}
