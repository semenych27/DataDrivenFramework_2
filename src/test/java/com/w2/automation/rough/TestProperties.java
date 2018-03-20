package com.w2.automation.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by s.milaserdov on 11/15/2017.
 */
public class TestProperties {

    public static void main(String[] args) throws IOException {

        Properties config = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\w2\\automation\\properties\\Config.properties");
        config.load(fis);

        Properties OR = new Properties();
        fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\w2\\automation\\properties\\OR.properties");
        OR.load(fis);

        System.out.println(config.getProperty("browser")) ;
        System.out.println(OR.getProperty("bmlBtn"));
    }
}
