package factory;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static utils.HelperUtils.convertToMap;
import static utils.HelperUtils.decodeBase64;

public final class AuthenticationFactory {
    private AuthenticationFactory(){}

    private static Function<Map<String, String>, AuthenticationScheme> oauth2CC = (parameters) -> {
        RequestSpecification requestSpecification =
                RestAssured.given().baseUri(parameters.get("base_url")).formParams(convertToMap(parameters.get("authParams"), true));

        Response response = requestSpecification.post("/token");

        PreemptiveOAuth2HeaderScheme oAuth2HeaderScheme = new PreemptiveOAuth2HeaderScheme();
        oAuth2HeaderScheme.setAccessToken(response.jsonPath().getString("access_token"));
        return oAuth2HeaderScheme;
    };

    private static Function<Map<String, String>, AuthenticationScheme> basic = (parameters) -> {
        String[] credentials = parameters.get("authParams").split(":");

        PreemptiveBasicAuthScheme basicAuthScheme = new PreemptiveBasicAuthScheme();
        basicAuthScheme.setUserName(decodeBase64(credentials[0]));
        basicAuthScheme.setPassword(decodeBase64(credentials[1]));

        return basicAuthScheme;
    };

    private static Map<String, Function<Map<String, String>, AuthenticationScheme>> authenticationMap = new HashMap();

    static {
        authenticationMap.put("oAuth2CC", oauth2CC);
        authenticationMap.put("Basic", basic);
    }

    public static AuthenticationScheme executeAuth(Map<String, String> parameters){
        return authenticationMap.get(parameters.get("authType")).apply(parameters);
    }
}
