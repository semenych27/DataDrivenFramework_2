package com.w2.automation.rough;

import com.w2.automation.utilities.MonitoringMail;
import com.w2.automation.utilities.TestConfig;

import javax.mail.MessagingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by s.milaserdov on 12/6/2017.
 */
public class HostAddress {

    public static void main(String[] args) throws UnknownHostException, MessagingException {

        MonitoringMail mail = new MonitoringMail();
        String messageBody= "http://" + InetAddress.getLocalHost().getHostAddress() + ":9090/job/DataDrivenFramework_2/Extent_Reports/";
        System.out.println(messageBody);

        mail.sendMail(TestConfig.server,TestConfig.from,TestConfig.to,TestConfig.port,TestConfig.subject,messageBody);
    }
}
