package com.w2.automation.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2.automation.utilities.ExcelReader;
import com.w2.automation.utilities.ExtentManager;
import com.w2.automation.utilities.TestUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by s.milaserdov on 11/15/2017.
 */
public class TestBase {

    public static WebDriver driver;

    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\java\\com\\w2\\automation\\testdata\\testdata.xlsx");
    public static WebDriverWait wait ;
    public ExtentReports report = ExtentManager.getInstance();
    public static ExtentTest test;
    public static String browser;

    @BeforeSuite
    public void setUp() throws IOException {

        if (driver == null) {

            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\w2\\automation\\properties\\Config.properties");
            config.load(fis);
            log.debug("Config file is loaded");

            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\w2\\automation\\properties\\OR.properties");
            OR.load(fis);
            log.debug("OR file is loaded");
        }

        if(System.getenv("browser")!=null ) {

            browser = System.getenv("browser");
            
        }else {

            browser = config.getProperty("browser");
        }

        config.setProperty("browser", browser);

        if (config.getProperty("browser").equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\java\\com\\w2\\automation\\executables\\geckodriver.exe");
            driver = new FirefoxDriver();
            log.debug("Firefox launched");
        } else if (config.getProperty("browser").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\java\\com\\w2\\automation\\executables\\chromedriver.exe");
            driver = new ChromeDriver();
            log.debug("Chrome launched");
        } else if (config.getProperty("browser").equals("ie")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\java\\com\\w2\\automation\\executables\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            log.debug("IE launched");
        }

        driver.get(config.getProperty("testsiteurl"));
        log.debug("Navigated to" + config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,5);

    }

    public void click(String locator){
        if(locator.endsWith("_CSS")){
            driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
        }else if(locator.endsWith("_XPATH")){
            driver.findElement(By.xpath(OR.getProperty(locator))).click();
        }
        test.log(LogStatus.INFO, "Clicking on: " + locator);
        
    }

    public void type(String locator, String value){
        if(locator.endsWith("_CSS")){
            driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
        }else if(locator.endsWith("_XPATH")){
            driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
        }
        test.log(LogStatus.INFO, "Entering value: " + value);
    }

    static WebElement dropdown;
    public void select(String locator, String value){
        if(locator.endsWith("_CSS")){
            dropdown =driver.findElement(By.cssSelector(OR.getProperty(locator)));
        }else if(locator.endsWith("_XPATH")){
            dropdown= driver.findElement(By.xpath(OR.getProperty(locator)));
        }

        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
        test.log(LogStatus.INFO, "Selecting from dropdown " + locator + "value " + value);
    }

    public boolean isElementPresent(By by) {

        try {

            driver.findElement(by);
            return true;

        } catch (NoSuchElementException e) {

            return false;

        }

    }

    /*public static boolean isElementPresent(String locator){
        int size = driver.findElements(By.cssSelector(locator)).size();

        if(size ==0){
            return true;
        } else {
            return false;
        }
    }*/

    public static void verifyEquals(String expected, String actual) throws IOException {

        try{

            Assert.assertEquals(actual,expected);

        }   catch (Throwable t){
            TestUtil.captureScreenshot();
            //ReportNG
            Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "</br>");
            Reporter.log("<a target =\"_blank\" href="+TestUtil.screenshotName+"><img src ="+TestUtil.screenshotName+" height=200 width=200></img></a>");
            Reporter.log("<br>");
            Reporter.log("<br>");
            //ExtentReport
            test.log(LogStatus.FAIL, " Verification failed with an exception: " + t.getMessage());
            test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
        }
    }

    @AfterSuite
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        log.debug("Test execution completed");
    }
}
