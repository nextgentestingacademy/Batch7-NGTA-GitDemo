package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestNGListener implements ITestListener{
	
	//Create a HTML report
	ExtentReports extent;
	
	//Create a test entry in the report
	ExtentTest test;
	
	//this will ensure that each thread has its own instance of ExtentTest, which is crucial for parallel test execution
	static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	@Override
	public void onStart(ITestContext context) {
		String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		
		reporter.config().setReportName("Automation Test Report");
		reporter.config().setDocumentTitle("District.in Report");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA Name",  "Rahul");
		extent.setSystemInfo("Environment", ConfigReader.get("env"));
	}
	

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test skipped");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		String path = "";
		WebDriver driver = DriverFactoryUtil.getDriver();
		extentTest.get().fail(result.getThrowable());
		try {
			path = ScreenshotUtil.captureScreenshot(driver, result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(path);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
