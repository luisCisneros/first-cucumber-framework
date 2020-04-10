package org.example;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    private static final Logger logger = LogManager.getLogger();
    public static final String PROPERTIES_PATH = "src/test/resources/config.properties";
    private Properties properties;
    private String url;
    private WebDriver driver;

    public String getUrl() {
        return url;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver setUpDriver() {
        logger.info("Properties file located at: {}", PROPERTIES_PATH);
        properties = getProperties(PROPERTIES_PATH);
        long timeout = Long.parseLong(properties.getProperty("timeout.in.seconds"));
        boolean isMaximizeWindow = Boolean.parseBoolean(properties.getProperty("maximize.window"));

        driver = selectWebDriver(properties);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        logger.info("Implicitly wait timeout set to {} seconds", timeout);
        driver.manage().deleteAllCookies();
        logger.info("All cookies deleted");
        if (isMaximizeWindow) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    public void loadWebPage(String website) {
        switch (website.toLowerCase()) {
            case "newegg":
                url = properties.getProperty("newegg.url");
                break;
            case "expedia":
                url = properties.getProperty("expedia.url");
                break;
            default:
                throw new IllegalArgumentException("Website [" + website + "] not valid.");
        }
        logger.info("Loading {} webpage...", url);
        driver.get(url);
    }

    public Properties getProperties(String path) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return properties;
    }

    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String testName = scenario.getName();
            scenario.embed(screenshot, "image/png", testName);
        }
        driver.quit();
    }

    private WebDriver selectWebDriver(Properties properties) {
        String browser = properties.getProperty("browser");
        String chromeDriverLocation = properties.getProperty("chrome.driver.location");
        String firefoxDriverLocation = properties.getProperty("firefox.driver.location");
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", firefoxDriverLocation);
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
                driver = new ChromeDriver();
                logger.info("Browser was not provided. Proceeding with Chrome");
                break;
        }
        logger.info("{} browser will be used for testing", browser);
        return driver;
    }
}
