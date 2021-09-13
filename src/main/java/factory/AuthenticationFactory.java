package factory;

import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static utils.AuthorizationUtils.getAuthToken;
import static utils.EncryptionUtils.decodeBase64;

public final class AuthenticationFactory {
    private AuthenticationFactory(){}

    private static BiFunction<RequestSpecification, Map<String, String>, RequestSpecification> oAuth2CC = (reqSpec, parameters) -> reqSpec.auth().oauth2(getAuthToken(parameters.get("base_url"), parameters.get("authParams").split(";")));

    private static BiFunction<RequestSpecification, Map<String, String>, RequestSpecification> basic = (reqSpec, parameters) -> {
        String[] credentials = parameters.get("authParams").split(":");
        return reqSpec.auth().preemptive().basic(decodeBase64(credentials[0]), decodeBase64(credentials[1]));
    };

    private static Map<String, BiFunction<RequestSpecification, Map<String, String>, RequestSpecification>> authenticationMap = new HashMap();

    static {
        authenticationMap.put("oAuth2CC", oAuth2CC);
        authenticationMap.put("Basic", basic);
    }

    public static RequestSpecification executeAuth(RequestSpecification reqSpec, Map<String, String> parameters){
        return authenticationMap.get(parameters.get("authType")).apply(reqSpec, parameters);
    }
}
