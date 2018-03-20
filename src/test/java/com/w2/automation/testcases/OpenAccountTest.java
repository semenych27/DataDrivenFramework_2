package com.w2.automation.testcases;

import com.w2.automation.base.TestBase;
import com.w2.automation.utilities.TestUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by s.milaserdov on 11/23/2017.
 */
public class OpenAccountTest extends TestBase {

    @Test(dataProviderClass = TestUtil.class,dataProvider = "openAccount")
    public void openAccountTest(String customer,String currency,String alertText) throws InterruptedException {

        if(!TestUtil.isTestRunnable("openAccountTest", excel)) {
            throw new SkipException("Skipping the test " + "openAccountTest".toUpperCase() + "as the Run mode is NO") ;
        }

        click("openAccBtn_CSS");
        select("customerDrop_CSS", customer);
        select("currencyDrop_CSS", currency);
        click("processBtn_CSS");

        Thread.sleep(2000);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alertText));
        alert.accept();
        Thread.sleep(2000);

    }

    

}
