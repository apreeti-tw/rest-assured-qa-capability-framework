package base;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import specifications.RequestSpecBuilder;

import java.util.Map;

public class BarnBaseTest {
    protected RequestSpecification newRequestSpec = null;

    @BeforeMethod
    public void setTestLevelRequestSpec(Object[] params){
        newRequestSpec = new RequestSpecBuilder()
                .getAuth((Map<String, String>) params[0])
                .getPathParams((Map<String, String>) params[0])
                .build();
    }
}
