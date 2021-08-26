package utils;

import enums.EndPoints;
import io.restassured.response.Response;

import static threadlocals.SpecManager.getRequestSpec;

public class HttpMethodUtils {
    private HttpMethodUtils(){}

    public static Response post(Object object){
        return getRequestSpec()
                .body(object)
                .post(EndPoints.POST_USER_REQUEST.getEndPoint());
    }

    public static Response delete(String id){
        return getRequestSpec().pathParams("id", id)
                .delete(EndPoints.DELETE_USER_REQUEST.getEndPoint());
    }
}
