package base;

import builders.RequestResponseSpecBuilder;
import builders.UserBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.Map;

import static enums.EndPoints.DELETE_USER_REQUEST;
import static enums.EndPoints.POST_USER_REQUEST;
import static utils.DataProviderUtils.getRunManagerData;

public class UserBaseTest {
    protected RequestSpecification requestSpecification;

    @BeforeTest
    public void beforeTest(ITestContext context, XmlTest xmlTest) throws IOException {
        context.setAttribute("testName", xmlTest.getName());
        context.setAttribute("runManager", getRunManagerData(xmlTest)[0]);
    }

    @BeforeMethod
    public void beforeMethod(ITestContext context, Object[] userData){
        Map<String, String> setupData = (Map<String, String>) context.getAttribute("runManager");
        Map<String, String> user = (Map<String, String>) userData[0];

        requestSpecification = new RequestResponseSpecBuilder()
                .get()
                .setBaseUri(setupData.get("base_url"))
                .build();

        if(user.get("createUser").equalsIgnoreCase("Y")){
            Response response = RestAssured.given().spec(requestSpecification).body(new UserBuilder(user).setUserData()).post(POST_USER_REQUEST.getEndPoint());

            context.setAttribute("user_id", response.jsonPath().get("id"));
        }
    }

    @AfterMethod
    public void afterMethod(Object[] userData, ITestContext context){
        Map<String, String> user = (Map<String, String>) userData[0];
        if(user.get("createUser").equalsIgnoreCase("N")){
            RestAssured.given(requestSpecification)
                    .pathParams("user_id", context.getAttribute("user_id"))
                    .delete(DELETE_USER_REQUEST.getEndPoint());
        }
    }
}
