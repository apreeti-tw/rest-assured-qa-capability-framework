package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.User;

import java.io.File;
import java.io.IOException;

public final class ObjectMapperUtils {
    private ObjectMapperUtils(){}

    public static Object getObjectFromJSON(String jsonFilePath, Class<User> targetClass) throws IOException {
        return new ObjectMapper().readValue(new File(jsonFilePath), targetClass);
    }
}
