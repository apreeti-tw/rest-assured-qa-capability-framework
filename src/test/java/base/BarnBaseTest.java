package base;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import specifications.RequestSpecBuilder;
import utils.DataProviderUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class BarnBaseTest {
    protected RequestSpecification newRequestSpec = null;

    @BeforeMethod
    public void setTestLevelRequestSpec(Method method) throws IOException {
        Map<String, String> params = (Map<String, String>) DataProviderUtils.getRunManagerData(method)[0];
        newRequestSpec = new RequestSpecBuilder()
                .setBaseUri(params.get("base_url"))
                .getAuth(params)
                .getPathParams(params)
                .build();
    }
}
