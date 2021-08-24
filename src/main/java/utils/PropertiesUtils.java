package utils;

import constants.FilePaths;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertiesUtils {

    private PropertiesUtils(){}

    private static Properties properties = new Properties();
    private static Map<String,String> CONFIG_MAP = new HashMap();

    static {
        try(FileInputStream fis = new FileInputStream(FilePaths.getPropertiesFilePath())) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        properties.forEach((key, value) -> CONFIG_MAP.put(String.valueOf(key), String.valueOf(value)));
    }

    public static String getProperty(String key){
        return CONFIG_MAP.get(key);
    }
}
