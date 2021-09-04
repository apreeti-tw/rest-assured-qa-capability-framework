package base;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;
import specifications.RequestSpecBuilder;
import utils.DataProviderUtils;

import java.io.IOException;
import java.util.Map;

public class BarnBaseTest {
    protected RequestSpecification newRequestSpec = null;

    @BeforeMethod
    public void setTestLevelRequestSpec(XmlTest xmlTest) throws IOException {
        Map<String, String> params = (Map<String, String>) DataProviderUtils.getRunManagerData(xmlTest)[0];
        newRequestSpec = new RequestSpecBuilder()
                .setBaseUri(params.get("base_url"))
                .getAuth(params)
                .getPathParams(params)
                .build();
    }
}
