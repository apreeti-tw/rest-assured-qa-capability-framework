package utils;

import java.util.HashMap;
import java.util.Map;

public final class HelperUtils {
    private HelperUtils(){}

    public static Map<String, String> convertToMap(String queryParams){
        Map<String,String> newMap = new HashMap<>();
        for (String keyValue: queryParams.split(";")) {
            newMap.put(keyValue.split(":")[0], keyValue.split(":")[1]);
        }
        return newMap;
    }
}
