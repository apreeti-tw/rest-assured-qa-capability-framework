package factory;

import io.restassured.authentication.*;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static utils.AuthorizationUtils.getAuthToken;
import static utils.EncryptionUtils.decodeBase64;

public final class AuthenticationFactory {
    private AuthenticationFactory(){}

    private static Function<Map<String, String>, AuthenticationScheme> basic = (parameters) -> {
        String[] credentials = parameters.get("authParams").split(":");

        PreemptiveBasicAuthScheme basicAuthScheme = new PreemptiveBasicAuthScheme();
        basicAuthScheme.setUserName(decodeBase64(credentials[0]));
        basicAuthScheme.setPassword(decodeBase64(credentials[1]));

        return basicAuthScheme;
    };

    private static Map<String, Function<Map<String, String>, AuthenticationScheme>> authenticationMap = new HashMap();

    static {
        authenticationMap.put("Basic", basic);
    }

    public static AuthenticationScheme executeAuth(Map<String, String> parameters){
        return authenticationMap.get(parameters.get("authType")).apply(parameters);
    }
}
