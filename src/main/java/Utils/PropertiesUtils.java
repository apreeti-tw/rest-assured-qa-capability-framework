package Utils;

import java.io.FileInputStream;
import java.io.IOException;

public final class PropertiesUtils {

    private PropertiesUtils(){}

    public static String getProperty(String property) throws IOException {
        java.util.Properties properties = new java.util.Properties();
        properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/application_test.properties"));
        return properties.getProperty(property);
    }
}
