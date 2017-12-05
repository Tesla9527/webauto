package com.test.modules;

import com.test.utils.DriverHelper;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;

import com.test.utils.Report;

public class JdPage {
    DriverHelper driverHelper;

    public JdPage(DriverHelper _driverHelper) {
        driverHelper = _driverHelper;
    }

    public void ClickJdAdLink() {
        try {
            //Get current method name
            Report.UpdateTestLogTitle(Thread.currentThread().getStackTrace()[1].getMethodName());

            //Go to jd homepage
            driverHelper.navigateTo("https://www.jd.com/");

            //Input "VR" in search box
            WebElement SearchBox = driverHelper.getElement("JdPage", "SearchBox");
            SearchBox.sendKeys("VR");

            //Click search button
            WebElement SearchButton = driverHelper.getElement("JdPage", "SearchButton");
            SearchButton.click();

            //Sleep 3000 ms to wait for search in result box displayed.
            Thread.sleep(3000);

            //Click search in result box
            WebElement SearchInResult = driverHelper.getElement("JdPage", "SearchInResult");
            SearchInResult.click();

            //Move by offset from search in result box to locate first VR ad link
            driverHelper.moveToElementByOffset(-1000, 0);

            Thread.sleep(2000);
            Report.UpdateTestLog("ClickJdAdLink", "Click first VR ad link successfully", Report.Status.PASS);
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Report.UpdateTestLog("ClickJdAdLink", "Click first VR ad link failed" + " | Error message is: " + e.toString(), Report.Status.FAIL);
        }
    }
}
