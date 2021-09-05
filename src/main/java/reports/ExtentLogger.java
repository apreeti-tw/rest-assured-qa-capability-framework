package reports;

public final class ExtentLogger {
    private ExtentLogger(){}

    public static void pass(String message){
        ExtentReportManager.getExtentTest().pass(message);
    }

    public static void fail(String message){
        ExtentReportManager.getExtentTest().fail(message);
    }
}
