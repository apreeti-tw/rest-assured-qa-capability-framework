package enums;

public enum EndPoints {
    POST_USER_REQUEST("/api/users"),
    DELETE_USER_REQUEST("/api/users/{user_id}"),
    UNLOCK_BARN_REQUEST("/api/{user_id}/barn-unlock"),
    ADD_BOOKS_LIST("/BookStore/v1/Books"),
    DELETE_BOOK_LIST("/BookStore/v1/Books");

    private String resource;

    EndPoints(String resource){
        this.resource = resource;
    }

    public String getEndPoint(){
        return resource;
    }
}
