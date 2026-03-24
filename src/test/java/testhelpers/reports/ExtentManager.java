package testhelpers.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    private static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports_"
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss")) + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("AwesomeQA Test Automation Report");
            sparkReporter.config().setDocumentTitle("Extent Report");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Tester", "QA Team");
            extentReports.setSystemInfo("Environment", "Testing");
        }
        return extentReports;
    }

    public static void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}