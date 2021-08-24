package pojo;

import java.util.Map;

public final class UserManager {

    private UserManager(){}

    public static User getUser(Map<String, String> user){
        return User.builder().name(user.get("name")).job(user.get("job")).build();
    }
}
