package builders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public final class RequestResponseSpecBuilder {
    private static RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
            .setUrlEncodingEnabled(false)
            .setContentType(ContentType.JSON);

    public static RequestSpecBuilder get(){
        return requestSpecBuilder;
    }
}
