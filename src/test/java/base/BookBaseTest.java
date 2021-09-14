package base;

import builders.RequestResponseSpecBuilder;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.Map;

import static enums.EndPoints.DELETE_BOOK_LIST;
import static factory.AuthenticationFactory.executeAuth;
import static utils.DataProviderUtils.getRunManagerData;
import static utils.HelperUtils.convertToMap;

public class BookBaseTest {
    protected RequestSpecification requestSpecification = null;

    @BeforeTest
    public void beforeTest(ITestContext context, XmlTest xmlTest) throws IOException {
        context.setAttribute("testName", xmlTest.getName());
        context.setAttribute("runManager", getRunManagerData(xmlTest)[0]);
    }

    @BeforeMethod
    public void setTestLevelRequestSpec(ITestContext context) {
        Map<String, String> params = (Map<String, String>) context.getAttribute("runManager");

        requestSpecification = new RequestResponseSpecBuilder().get()
                .setBaseUri(params.get("base_url"))
                .setAuth(executeAuth(params))
                .setAuth(executeAuth(params))
                .build();
    }

    @AfterMethod
    public void deleteBookFromUser(ITestContext context) {
        Map<String, String> params = (Map<String, String>) context.getAttribute("runManager");
        RestAssured.given(requestSpecification).queryParams(convertToMap(params.get("queryParams"), false)).delete(DELETE_BOOK_LIST.getEndPoint());
    }
}
