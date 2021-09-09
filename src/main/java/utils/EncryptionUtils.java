package utils;

import java.util.Base64;

public final class EncryptionUtils {
    private EncryptionUtils(){}

    public static String decodeBase64(String string) {
        return new String(Base64.getDecoder().decode(string));
    }
}
