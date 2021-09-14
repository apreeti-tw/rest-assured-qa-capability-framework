package utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public final class HelperUtils {
    private HelperUtils(){}

    public static Map<String, String> convertToMap(String queryParams, boolean decode){
        Map<String,String> newMap = new HashMap<>();
        for (String keyValue: queryParams.split(";")) {
            String key = keyValue.split(":")[0];
            String value = keyValue.split(":")[1];
            if(decode)  value = decodeBase64(value);
            newMap.put(key, value);
        }
        return newMap;
    }

    public static String decodeBase64(String value) {
        return new String(Base64.getDecoder().decode(value));
    }
}
