package threadlocals;

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
}
