package com.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.resources.ExtendReporter;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
ExtentReports extent = ExtendReporter.extendReport();
ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
@Override
public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub
	test = extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);//unique thread id(ErrorValidationTest)->test
}

@Override
public void onTestSuccess(ITestResult result) {
	// TODO Auto-generated method stub
	extentTest.get().log(Status.PASS, "Test Passed");
	
}

@Override
public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub
	extentTest.get().fail(result.getThrowable());//
	
	try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
				.get(result.getInstance());
		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	
	String filePath = null;
	try {
		
		filePath = getScreenShot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	
	
	//Screenshot, Attach to report
	
	
}

@Override
public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onStart(ITestContext context) {
	// TODO Auto-generated method stub
	
}

@Override
public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
	extent.flush();
	
}
//    @Override
//    public void onFinish(ITestContext arg0) {
//        // TODO Auto-generated method stub
//    	System.out.print("Finishing the test cases");
//    	extent.flush();
//    }
//
//    @Override
//    public void onStart(ITestContext arg0) {
//        // TODO Auto-generated method stub
//       extent.createTest(arg0.getName());
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'onTestFailedButWithinSuccessPercentage'");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult arg0) {
//        test.fail(arg0.getThrowable());
//        String path = null;
//        try {
//            driver = (WebDriver) arg0.getTestClass().getRealClass().getField("driver").get(arg0.getInstance());
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } 
//        
//       try {
//        path =  getScreenShot(arg0.getMethod().getMethodName(),driver);
//       
//    } catch (IOException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
//    test.addScreenCaptureFromPath(path,arg0.getMethod().getMethodName());
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult arg0) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'onTestSkipped'");
//    }
//
//    @Override
//    public void onTestStart(ITestResult arg0) {
//        // TODO Auto-generated method stub
//        extent.createTest(arg0.getMethod().getMethodName());
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult arg0) {
//        // TODO Auto-generated method stub
//        test.log(Status.PASS, "Pass");
//    }
    
}
