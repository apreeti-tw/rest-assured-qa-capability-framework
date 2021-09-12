package base;

import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;
import builders.RequestSpecBuilder;

import java.io.IOException;
import java.util.Map;

import static utils.DataProviderUtils.getRunManagerData;

public class BarnBaseTest {
    protected RequestSpecification newRequestSpec = null;

    @BeforeTest
    public void beforeTest(ITestContext context, XmlTest xmlTest){
        context.setAttribute("testName", xmlTest.getName());
    }

    @BeforeMethod
    public void setTestLevelRequestSpec(XmlTest xmlTest) throws IOException {
        Map<String, String> params = (Map<String, String>) getRunManagerData(xmlTest)[0];
        newRequestSpec = new RequestSpecBuilder()
                .setBaseUri(params.get("base_url"))
                .setAuth(params)
                .setPathParams(params)
                .build();
    }
}
