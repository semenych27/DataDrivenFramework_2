package com.w2.automation.testcases;

import com.w2.automation.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by s.milaserdov on 11/16/2017.
 */
public class LoginTest extends TestBase{

    @Test
    public void loginAsBAnkManager() throws InterruptedException, IOException {

        verifyEquals("abc","xyz");
        Thread.sleep(3000);
        log.debug("Inside LoginTest");
        click("bmlBtn_CSS");
        //driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();

        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomBtn_CSS"))), "Login not successfull");

        log.debug("Login successfully");
        Reporter.log("Login successfully executed");
        //Assert.fail("Login not successfull");

    }
}
