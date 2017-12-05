package com.test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserHelper {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BrowserHelper() {
        this.initialBrowser();
    }

    private void initialBrowser() {
        String toolPath = "D:\\WebAuto\\tools\\";
        String browserType = "chrome";
        switch (browserType) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", toolPath + "chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                System.setProperty("webdriver.chrome.driver", toolPath + "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.chrome.driver", toolPath + "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
        }
    }
}
