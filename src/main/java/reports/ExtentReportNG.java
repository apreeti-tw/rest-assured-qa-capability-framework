package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.FilePaths;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReportNG {
    private ExtentReportNG(){}

    private static ExtentReports extentReports;

    public static void initReports() {
        if(Objects.isNull(extentReports)){
            extentReports = new ExtentReports();
            FilePaths.createFile();
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FilePaths.getExtentReportFilePath());
            extentSparkReporter.config().setReportName("Orange HRM Test Demo");
            extentSparkReporter.config().setDocumentTitle("Test Results");
            extentReports.attachReporter(extentSparkReporter);
        }
    }

    public static void flushReports() throws IOException {
        if (Objects.nonNull(extentReports)){
            ExtentReportManager.unload();
            extentReports.flush();
        }
        Desktop.getDesktop().browse(new File(FilePaths.getExtentReportFilePath()).toURI());
    }

    public static void createTestReport(String testName){
        ExtentReportManager.setExtentTest(extentReports.createTest(testName));
    }
}
