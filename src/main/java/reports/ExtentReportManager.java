package reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentReportManager {
    private ExtentReportManager(){}

    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static ExtentTest getExtentTest(){
        return extentTestThreadLocal.get();
    }

    public static void setExtentTest(ExtentTest extentTest){
        extentTestThreadLocal.set(extentTest);
    }

    public static void unload(){
        extentTestThreadLocal.remove();
    }
}
