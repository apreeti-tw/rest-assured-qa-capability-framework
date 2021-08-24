package enums;

public enum EndPoints {
    POST_USER_REQUEST("/api/users"),
    DELETE_USER_REQUEST("/api/users/{id}");

    private String resource;

    EndPoints(String resource){
        this.resource = resource;
    }

    public String getEndPoint(){
        return resource;
    }
}
