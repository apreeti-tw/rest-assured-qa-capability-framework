package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class ParseDynamicJson {
    public static Object obj=null;

    public static Object parseObject(JSONObject json, String key) throws JSONException {
        System.out.println("Dynamic key value is " + json.get(key));
        return json.get(key);
    }


    public static Object getKey(JSONObject json, String key) throws JSONException {

        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;
        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if (json.get(nextKeys) instanceof JSONObject) {
                       getKey(json.getJSONObject(nextKeys), key);
                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonArray = json.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String jsonArrayString = jsonArray.get(i).toString();
                            if (!jsonArrayString.startsWith("{"))
                                continue;
                            JSONObject innerJson = new JSONObject(jsonArrayString);
                            getKey(innerJson, key);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else
           obj= parseObject(json, key);
        return obj;
    }


    public static Object ValidateJson(String JsonResponse, String key) throws JSONException {
        Object obj = null;
        if (JsonResponse.startsWith("[")) {
            JSONArray inputJsonArray = new JSONArray(JsonResponse);
            for (int i = 0; i < inputJsonArray.length(); i++) {
                String jsonArrayString = inputJsonArray.get(i).toString();
                JSONObject innerJson = new JSONObject(jsonArrayString);
                obj=getKey(innerJson, key);
            }
        } else {
            JSONObject inputJson = new JSONObject(JsonResponse);
               obj=getKey(inputJson, key);
        }
        return obj;
    }

    public static void main(String[] args) throws JSONException {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/ComplexJson.json");
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(String.valueOf(file))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File content is "+ content);
      Object obj= ParseDynamicJson.ValidateJson(content, "purchaseAmount");
        System.out.println("Parse Json is "+obj);
    }


}
