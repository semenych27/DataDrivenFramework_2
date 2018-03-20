package com.w2.automation.listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.w2.automation.base.TestBase;
import com.w2.automation.utilities.TestUtil;
import org.testng.*;

import java.io.IOException;

/**
 * Created by s.milaserdov on 11/24/2017.
 */
public class CustomListeners extends TestBase implements ITestListener {
    
    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.setProperty("org.uncommons.reportng.escape-output", "false"); //we need this to see "Screenshot" text as a link in reportng
        //Reporter.log("Capturing screeenshot"); //this message will be shown in ReportNG test-output/html/index.html  if we run testng.xml or in target/surefire-report/html/index.html if we run via Maven
        //Reporter.log("<a target = \"_blank\" href=\"C:\\Users\\s.milaserdov\\Desktop\\Screenshot\\error.png\">Screenshot</a>");
        TestBase.test.log(LogStatus.PASS, iTestResult.getName().toUpperCase()+ "PASS");
        report.endTest(test);
        report.flush();
    }

    public void onTestFailure(ITestResult iTestResult) {

        System.setProperty("org.uncommons.reportng.escape-output", "false"); //we need this to see "Screenshot" text as a link in reportng
        try {
            TestUtil.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }

        test.log(LogStatus.FAIL, iTestResult.getName().toUpperCase() + "Failed with an exception " + iTestResult.getThrowable());
        test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
        report.endTest(test);
        report.flush();
        
        Reporter.log("Click to see screenshot"); //this message will be shown in ReportNG test-output/html/index.html  if we run testng.xml or in target/surefire-report/html/index.html if we run via Maven
        Reporter.log("<a target = \"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target = \"_blank\" href="+TestUtil.screenshotName+"><img src ="+TestUtil.screenshotName+"height=200 width=200></img></a>");   // to show image of the failure
    }

    public void onTestSkipped(ITestResult iTestResult) {
        test.log(LogStatus.SKIP, iTestResult.getName().toUpperCase() + " Skipped the test as run mode is NO");
        report.endTest(test);
        report.flush();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

        test = report.startTest(iTestContext.getName().toUpperCase());



    }

    public void onFinish(ITestContext iTestContext) {

    }
}
