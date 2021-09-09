package builders;

import pojo.User;

import java.util.Map;

public final class UserBuilder {
    private Map<String, String> user;

    public UserBuilder(Map<String, String> user){
        this.user = user;
    }

    public User setUserData() {
        return User.builder().name(user.get("name")).job(user.get("job")).build();
    }
}
