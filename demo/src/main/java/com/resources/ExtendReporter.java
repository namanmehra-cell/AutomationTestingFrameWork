package com.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporter {
    public static ExtentReports extendReport(){
        String path = System.getProperty("user.dir")+"//reports//Report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Testing Report");
        reporter.config().setDocumentTitle("Test Results");
        
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA Tester", "XYZ.org");
        return extent;
    }
}
