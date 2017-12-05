package com.test.utils;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.ho.yaml.Yaml;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class DriverHelper {
    private WebDriver driver;
    private HashMap<String, HashMap<String, String>> ml;

    public DriverHelper(WebDriver _driver) {
        driver = _driver;
    }

    private By getBy(String type, String value) {
        By by = null;
        switch (type) {
            case "Id":
                by = By.id(value);
                break;
            case "Name":
                by = By.name(value);
                break;
            case "XPath":
                by = By.xpath(value);
                break;
            case "ClassName":
                by = By.className(value);
                break;
            case "LinkText":
                by = By.linkText(value);
                break;
            case "CssSelector":
                by = By.cssSelector(value);
        }
        return by;
    }

    public WebElement getElement(String uiMapFile, String key) throws FileNotFoundException {
        File f = new File("D://WebAuto//src//com//test//uimap//" + uiMapFile + ".yaml");
        ml = Yaml.loadType(new FileInputStream(f.getAbsolutePath()), HashMap.class);
        String type = ml.get(key).get("type");
        String value = ml.get(key).get("value");
        return driver.findElement(this.getBy(type, value));
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void moveToElementByOffset(int x, int y) {
        Actions builder = new Actions(driver);
        Actions hoverClick = builder.moveByOffset(x, y).click();
        hoverClick.build().perform();
    }
}
