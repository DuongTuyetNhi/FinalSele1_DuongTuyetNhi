package BaseTest;

import base.DriverManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static base.DriverManagement.getDriver;

public class BaseTest {
    @BeforeMethod
    public synchronized void beforeMethod() throws Throwable {
        System.out.println("Pre-condition");
        DriverManagement.initDriver();
    }

    @AfterMethod
    public synchronized void afterMethod() {
        System.out.println("Post-condition");
        getDriver().quit();
    }
//
//    @BeforeTest
//    public void browserLaunch() throws Exception {
//        DriverManagement.initDriver();
//    }
}
