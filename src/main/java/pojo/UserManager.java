package pojo;

public class UserManager {

    public User getUser(String name, String job){
        return User.builder().name(name).job(job).build();
    }
}
