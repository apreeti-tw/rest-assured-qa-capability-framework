package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.FilePaths;
import enums.Constants;
import utils.PropertiesUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static enums.Constants.AUTO_OPEN_REPORT_YES;

public final class ExtentReportNG {
    private ExtentReportNG(){}

    private static ExtentReports extentReports;

    public static void initReports() {
        if(Objects.isNull(extentReports)){
            extentReports = new ExtentReports();
            FilePaths.createFile();
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FilePaths.getExtentReportFilePath());
            extentSparkReporter.config().setReportName("RestAssured Test Demo");
            extentSparkReporter.config().setDocumentTitle("Test Results");
            extentReports.attachReporter(extentSparkReporter);
        }
    }

    public static void flushReports() throws IOException {
        if (Objects.nonNull(extentReports)){
            ExtentReportManager.unload();
            extentReports.flush();
        }
        if(PropertiesUtils.getProperty("autoOpenReports").equalsIgnoreCase(AUTO_OPEN_REPORT_YES.name()))
            Desktop.getDesktop().browse(new File(FilePaths.getExtentReportFilePath()).toURI());
    }

    public static void createTestReport(String testName){
        ExtentReportManager.setExtentTest(extentReports.createTest(testName));
    }
}
