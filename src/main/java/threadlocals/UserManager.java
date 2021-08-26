package threadlocals;

import pojo.User;

import java.util.Map;

public final class UserManager {

    private UserManager(){}

    private static ThreadLocal<String> idThreadLocal = new ThreadLocal<>();

    public static String getId(){
        return idThreadLocal.get();
    }

    public static void setId(String id){
        idThreadLocal.set(id);
    }

    public static void unload(){
        idThreadLocal.remove();
    }

    public static User getUser(Map<String, String> user){
        return User.builder().name(user.get("name")).job(user.get("job")).build();
    }
}
