package com.test.testcases;

import com.test.modules.JdPage;
import com.test.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class _001_CheckJdLink {

    @Test
    public void _001_CheckJdLink() {
        jdPage.ClickJdAdLink();
    }

    private WebDriver driver;
    private JdPage jdPage;

    @BeforeTest
    public void Initialize(){
        InitializeReport(this.getClass().getSimpleName());
        BrowserHelper browserHelper = new BrowserHelper();
        driver = browserHelper.getDriver();
        DriverHelper driverHelper = new DriverHelper(driver);
        jdPage = new JdPage(driverHelper);
    }

    @AfterTest
    public void Cleanup() {
        driver.close();
        driver.quit();
        CloseReport();
        Assert.assertEquals(0, Report.getfailno());
    }

    public static void InitializeReport(String reportName){
        boolean takeScreenshotFailedStep = true;
        boolean takeScreenshotPassedStep = false;
        String reportPath = "D:\\WebAuto\\src\\com\\test\\report\\";
        Report.InitialReport(reportPath, reportName, takeScreenshotFailedStep, takeScreenshotPassedStep);
        Report.CreateTestLogHeader("Project Name Demo");
        Report.setstepno(0);
        Report.setpassno(0);
        Report.setfailno(0);
        Report.setstarttime(DateHelper.GetCurrentDate());
    }

    public static void CloseReport() {
        Report.setendtime(DateHelper.GetCurrentDate());
        Report.CreateTestLogFooter(DateHelper.GetTimeDifference(Report.getstarttime(), Report.getendtime()), Report.getpassno(), Report.getfailno());
    }
}
