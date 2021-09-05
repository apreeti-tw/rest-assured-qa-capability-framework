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
    public Object[] getData (Method method, ITestContext context) throws IOException {
        List<Map<String,String>> listOfData = ExcelUtils.getTestData(getProperty((String) context.getAttribute("testName")));

        return listOfData
                .parallelStream()
                .filter(val -> val.get("testname").equalsIgnoreCase(method.getName()))
                .toArray();
    }

    @DataProvider(name = "RunManager")
    public static Object[] getRunManagerData (XmlTest xmlTest) throws IOException {
        List<Map<String,String>> listOfData = ExcelUtils.getTestData("RunManager");

        return listOfData
                .parallelStream()
                .filter(val -> val.get("testname").equalsIgnoreCase(xmlTest.getName()))
                .toArray();
    }
}
