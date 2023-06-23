package com.saucelabs.utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Driver {
    private Driver() {

    }

    private static WebDriver driver;
    private static RemoteWebDriver remoteDriver;

    public static WebDriver get() throws MalformedURLException {

        if (driver == null) {
            String browser = ConfigurationReader.get("browser");
            switch (browser) {
                case "saucelabs":
                    MutableCapabilities sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("username", "oauth-wirelisa35-46071");
                    sauceOptions.setCapability("accessKey", "c0e185e9-8f85-464c-abbf-c8e941fb89a3");
               //     sauceOptions.setCapability("name", method.getName());
                    sauceOptions.setCapability("browserName","chrome");
                    sauceOptions.setCapability("browserVersion", "latest");
                    sauceOptions.setCapability("name","SauceTest");
                    ChromeOptions options = new ChromeOptions();
                    options.setCapability("sauce:options", sauceOptions);
                    URL url = new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");
                    driver = new RemoteWebDriver(url, options);
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options1 = new ChromeOptions();
                    options1.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking","enable-automation"));
                    options1.addArguments("disable-notifications");
                    options1.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    options1.addArguments( "--disable-blink-features=AutomationControlled");
                    options1.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options1);
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;
            }

        }

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}