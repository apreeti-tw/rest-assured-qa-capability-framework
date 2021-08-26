package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.User;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public final class ObjectMapperUtils {
    private ObjectMapperUtils(){}

    public static Object getObjectFromJSON(String jsonFilePath, Class<User> targetClass) throws IOException {
        return new ObjectMapper().readValue(new File(jsonFilePath), targetClass);
    }

    public static User getUser(Map<String, String> user){
        return User.builder().name(user.get("name")).job(user.get("job")).build();
    }
}
