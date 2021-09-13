package builders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public final class RequestResponseSpecBuilder {
    private RequestSpecBuilder requestSpecBuilder;

    public RequestResponseSpecBuilder(){
        requestSpecBuilder = new RequestSpecBuilder()
                .setUrlEncodingEnabled(false)
                .setContentType(ContentType.JSON);
    }

    public RequestSpecBuilder get(){
        return requestSpecBuilder;
    }
}
