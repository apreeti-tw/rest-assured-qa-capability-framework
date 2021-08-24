package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class DataProviderUtils {

    @DataProvider(name = "DataContainer")
    public Object[] getData () throws IOException {
        List<Map<String,String>> listOfData = ExcelUtils.getTestData("UserData");

        return listOfData
                .parallelStream()
                .filter(val -> val.get("testname").equalsIgnoreCase("testPostUser"))
                .toArray();
    }
}
