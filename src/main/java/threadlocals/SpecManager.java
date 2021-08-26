package threadlocals;

import io.restassured.specification.RequestSpecification;

public class SpecManager {
    private SpecManager(){}

    private static ThreadLocal<RequestSpecification> reqSpecThreadLocal = new ThreadLocal<>();

    public static RequestSpecification getRequestSpec(){
        return reqSpecThreadLocal.get();
    }

    public static void setRequestSpec(RequestSpecification requestSpec){
        reqSpecThreadLocal.set(requestSpec);
    }

    public static void unload(){
        reqSpecThreadLocal.remove();
    }

}
