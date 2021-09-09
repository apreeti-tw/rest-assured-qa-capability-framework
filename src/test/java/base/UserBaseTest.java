package base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;
import builders.RequestSpecBuilder;

import java.io.IOException;
import java.util.Map;

import static enums.EndPoints.DELETE_USER_REQUEST;
import static enums.EndPoints.POST_USER_REQUEST;
import static utils.DataProviderUtils.getRunManagerData;
import static utils.ObjectMapperUtils.getUser;

public class UserBaseTest {
    protected RequestSpecification requestSpecification;

    @BeforeTest
    public void beforeTest(ITestContext context, XmlTest xmlTest){
        context.setAttribute("testName", xmlTest.getName());
    }

    @BeforeMethod
    public void beforeMethod(XmlTest xmlTest, ITestContext context, Object[] userData) throws IOException {
        Map<String, String> setupData = (Map<String, String>) getRunManagerData(xmlTest)[0];
        Map<String, String> user = (Map<String, String>) userData[0];
        requestSpecification = new RequestSpecBuilder().setBaseUri(setupData.get("base_url")).build();

        if(user.get("createUser").equalsIgnoreCase("Y")){
            Response response = requestSpecification.body(getUser(user)).post(POST_USER_REQUEST.getEndPoint());
            context.setAttribute("user_id", response.jsonPath().get("id"));
        }
    }

    @AfterMethod
    public void afterMethod(Object[] userData, ITestContext context){
        Map<String, String> user = (Map<String, String>) userData[0];
        if(user.get("createUser").equalsIgnoreCase("N")){
            requestSpecification.pathParams("user_id", context.getAttribute("user_id")).delete(DELETE_USER_REQUEST.getEndPoint());
        }
    }
}
