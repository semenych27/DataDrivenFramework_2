package com.w2.automation.utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

/**
 * Created by s.milaserdov on 11/27/2017.
 */
public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance(){

        if(extent == null){

            extent = new ExtentReports(System.getProperty("user.dir")+ "\\target\\surefire-reports\\html\\extent.html", true, DisplayOrder.OLDEST_FIRST);
            extent.loadConfig(new File(System.getProperty("user.dir") + "\\src\\com\\w2\\automation\\extentconfig\\ReportsConfig.xml"));
        }

        return extent;
    }
}
