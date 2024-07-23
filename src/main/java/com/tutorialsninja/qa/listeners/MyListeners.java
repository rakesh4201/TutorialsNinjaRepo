package com.tutorialsninja.qa.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		 extentReport = ExtentReporter.generateExtentReporter();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
	    extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+"started executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS,testName+"got successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		
		
		WebDriver driver=null;
			try {
				 driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
	
			File srcScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destinationScreenshot = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
			try {
				FileHandler.copy(srcScreenShot, new File(destinationScreenshot));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		extentTest.addScreenCaptureFromPath(destinationScreenshot);
		extentTest.log(Status.FAIL,testName+"got successfully executed");
		extentTest.log(Status.INFO, result.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+"got skipped");
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
	}

}
