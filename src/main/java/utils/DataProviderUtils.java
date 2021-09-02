package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public final class DataProviderUtils {

    @DataProvider(name = "DataContainer")
    public Object[] getData (Method method) throws IOException {
        List<Map<String,String>> listOfData = ExcelUtils.getTestData("UserData");

        return listOfData
                .parallelStream()
                .filter(val -> val.get("testname").equalsIgnoreCase(method.getName()))
                .toArray();
    }

    @DataProvider(name = "BarnContainer")
    public Object[] getBarnData (Method method) throws IOException {
        List<Map<String,String>> listOfData = ExcelUtils.getTestData("BarnData");

        return listOfData
                .parallelStream()
                .filter(val -> val.get("testname").equalsIgnoreCase(method.getName()))
                .toArray();
    }
}
