package com.w2.automation.rough;

import java.util.Date;

/**
 * Created by s.milaserdov on 11/24/2017.
 */
public class TestTimeStamp {

    public static void main(String[] args) {

        Date d = new Date();
        String screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
        System.out.println(screenshotName);
        System.out.println(d);
    }


    

}
