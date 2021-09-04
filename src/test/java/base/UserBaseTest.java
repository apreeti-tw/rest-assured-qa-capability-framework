package base;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.xml.XmlTest;
import specifications.RequestSpecBuilder;
import utils.DataProviderUtils;

import java.io.IOException;
import java.util.Map;

public class UserBaseTest {
    protected RequestSpecification requestSpecification;

    @BeforeMethod
    public void beforeMethod(XmlTest xmlTest) throws IOException {
        Map<String, String> setupData = (Map<String, String>) DataProviderUtils.getRunManagerData(xmlTest)[0];
        requestSpecification = new RequestSpecBuilder().setBaseUri(setupData.get("base_url")).build();
    }
}
