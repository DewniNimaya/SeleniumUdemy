package base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends BasePage {

	public static ExtentReports extentReport;
	public static String extentReportPrefix;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	public ExtentManager() throws IOException {
		super();
		
	}
	
	//cheking if the extentReport field is a null value. If the extent report instance is null, it sets up a new extent report with the project name "LiveProject" and returns it
	public static ExtentReports getReport() {
		
		if(extentReport == null) {
			
			setUpExtentReport("LiveProject");
		}
		return extentReport;
		
	}
	//setting up extent reports
	public static ExtentReports setUpExtentReport(String testName) {
		
		extentReport = new ExtentReports();
		//defining the location where the reports are saved
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/report/" + extentReportsPrefix_Name(testName) + ".html");
		extentReport.attachReporter(spark);
		
		//sets up additional information for the report such as tester name, report name, document title, and theme
		extentReport.setSystemInfo("Tester", "Dewni Alahakoon");
		spark.config().setReportName("Regression Test");
		spark.config().setDocumentTitle("Test Results");
		spark.config().setTheme(Theme.STANDARD);
		
		return extentReport;
	}
	
	//defines a method extentReportsPrefix_Name() which generates a prefix for the extent report file name based on the test name.
	public static String extentReportsPrefix_Name(String testName) {
		
		String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		extentReportPrefix = testName + "_" + date;
		return extentReportPrefix;
	}
	//flushing/closing the extent report, ensuring that all information is properly written to the report file before it is closed.
	public static void flushReport() {
		extentReport.flush();
	}
	
	public synchronized static ExtentTest getTest() {
		return extentTest.get();
	}
	
	public synchronized static ExtentTest createTest(String name, String description) {
		ExtentTest test = extentReport.createTest(name, description);
		extentTest.set(test);
		return getTest();
	}
	
	public synchronized static void log(String message) {
		getTest().info(message);
	}
	
	public synchronized static void pass(String message) {
		getTest().pass(message);
	}
	
	public synchronized static void fail(String message) {
		getTest().fail(message);
	}
	
	public synchronized static void attachImage() {
		getTest().addScreenCaptureFromPath(getScreenshotDestinationPath());
	}
}
