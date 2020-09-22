package com.milestone.listener;

import com.aventstack.extentreports.Status;
import com.milestone.devicefactory.DeviceStore;
import com.milestone.report.ExtentManager;
import com.milestone.report.ExtentTestManager;
import org.testng.*;
import org.testng.internal.annotations.IListeners;


public class WebDriverListener implements IInvokedMethodListener, ISuiteListener, ITestListener {


    @Override
    public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult testResults) {

        ITestContext context = testResults.getTestContext();

        //platform should be defined in testNG suite file
        context.setAttribute("platform",
                testResults.getTestContext().getSuite().getParameter("platform"));

        DeviceStore.setPlatform(context.getAttribute("platform").toString());

    }

    @Override
    public void onFinish(ISuite suite) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onStart(ISuite suite) {

        System.out.println("Starting suite " + suite.getName());

        String platform = suite.getXmlSuite().getParameter("platform");
        if (platform.equalsIgnoreCase("ios")) {
            suite.getXmlSuite().setThreadCount(DeviceStore.getIOSDeviceCount());
        }

        if (platform.equalsIgnoreCase("android")) {
            suite.getXmlSuite().setThreadCount(DeviceStore.getAndroidDeviceCount());
        }
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }
}