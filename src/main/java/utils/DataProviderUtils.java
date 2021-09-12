package utils;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static utils.PropertiesUtils.getProperty;

public final class DataProviderUtils {

    @DataProvider(name = "DataContainer")
    public static Object[] getData (Method method, ITestContext context) throws IOException {
        return filterTestData(ExcelUtils.getTestData(getProperty((String) context.getAttribute("testName"))), method.getName());
    }

    @DataProvider(name = "RunManager")
    public static Object[] getRunManagerData (XmlTest xmlTest) throws IOException {
        return filterTestData(ExcelUtils.getTestData(getProperty("RunManager")), xmlTest.getName());
    }

    @DataProvider(name = "ComplexDataContainer")
    public static Object[] getData (String sheetName, String methodName) throws IOException {
        return filterTestData(ExcelUtils.getTestData(sheetName), methodName);
    }

    public static Object[] filterTestData(List<Map<String,String>> listOfData, String filter){
        return listOfData
                .parallelStream()
                .filter(val -> val.get("testname").equalsIgnoreCase(filter))
                .toArray();
    }
}
