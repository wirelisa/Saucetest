package com.saucelabs.tests;

import com.saucelabs.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class sauceTest {
    protected WebDriver driver;
    protected Actions action;
    protected WebDriverWait wait;
    protected String url;

    protected RemoteWebDriver remoteDriver;
/*
    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "oauth-wirelisa35-46071");
        sauceOptions.setCapability("accessKey", "c0e185e9-8f85-464c-abbf-c8e941fb89a3");
        sauceOptions.setCapability("name", method.getName());
        sauceOptions.setCapability("browserVersion", "latest");

        ChromeOptions options = new ChromeOptions();
        options.setCapability("sauce:options", sauceOptions);
        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");

        remoteDriver = new RemoteWebDriver(url, options);
    }
*/

    @Test
    public void runOnCloud() throws MalformedURLException {
        driver = Driver.get();
        driver.get("https://www.saucedemo.com");
        // Inputs standard_user in username field.
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        // Inputs secret_sauce in password field
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        // Clicks the Login button
        driver.findElement(By.id("login-button")).click();

        String pageTitle=driver.getTitle();
        Assert.assertEquals(pageTitle,"Swag Labs");


        // maximizes browser window
        driver.manage().window().maximize();

        // Closes browser
        driver.quit();


    }


}
