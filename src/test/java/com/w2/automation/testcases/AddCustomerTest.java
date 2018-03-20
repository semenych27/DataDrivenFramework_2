package com.w2.automation.testcases;

import com.w2.automation.base.TestBase;
import com.w2.automation.utilities.TestUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by s.milaserdov on 11/23/2017.
 */
public class AddCustomerTest extends TestBase {

    @Test(dataProviderClass = TestUtil.class,dataProvider = "getData")
    public void addCustomer(String firstName, String lastName, String postCode, String alertText, String runMode) throws InterruptedException {

        if(!runMode.equals("Y")){

            throw new SkipException("Skipping the test case as the runmode for data set is NO");
        }

        //just a comment
        click("addCustomBtn_CSS");
        //driver.findElement(By.cssSelector(OR.getProperty("addCustomBtn"))).click();
        type("firstname_CSS",firstName);
        //driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
         type("lastname_CSS",lastName);
        //driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
        type("postcode_CSS",postCode);
        //driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
        click("addCustBt_CSS");
        //driver.findElement(By.cssSelector(OR.getProperty("addCustBt"))).click();

        Thread.sleep(3000);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alertText));
        alert.accept();

    }

    }
