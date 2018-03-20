package com.w2.automation.utilities;

import com.w2.automation.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by s.milaserdov on 11/24/2017.
 */
public class TestUtil extends TestBase {

    public static String screenshotpath;
    public static String screenshotName;
    public static void captureScreenshot() throws IOException {

       File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        Date d = new Date();
        screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg"; //adds date as screenshot name
        FileUtils.copyFile(scrFile,new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));



    }

    @DataProvider
    public static Object[][] getData() {

        return getObjects("AddCustomerTest");

    }

    @DataProvider
    public static Object[][] openAccount() {
        return getObjects("OpenAccountTest");

    }

    private static Object[][] getObjects(String sheetName) {
        System.out.println(sheetName);
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        System.out.println("Total rows are : " + rows);
        System.out.println("Total cols are : " + cols);

        Object [] []data = new Object[rows - 1][cols];

        for (int rowNum = 2; rowNum <= rows; rowNum++) { //2

            for (int colNum = 0; colNum < cols; colNum++) {

                //data[0][0]
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum); //-2
            }


        }
        return data;
    }

    public static boolean isTestRunnable(String testName, ExcelReader excelReader)  {

        String sheetName = "test_suite";
        int rows = excel.getRowCount(screenshotName);

        for(int rNum = 2; rNum <= rows; rNum++ ){
            String testCase = excel.getCellData(screenshotName,"TCID", rNum );

            if(testCase.equalsIgnoreCase(testName)) {
                String runmode = excel.getCellData(sheetName,"Runmode",rNum);

                if(runmode.equalsIgnoreCase("Y")){
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }
}
