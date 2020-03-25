package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.io.Files;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    private static final Logger logger = LogManager.getLogger();
    private String url;
    private WebDriver driver;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver setUpDriver() {
        String propertiesPath = "src/test/resources/config.properties";
        logger.info("Properties file located at: {}", propertiesPath);
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(propertiesPath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        String browser = properties.getProperty("browser");
        String chromeDriverLocation = properties.getProperty("chrome.driver.location");
        String firefoxDriverLocation = properties.getProperty("firefox.driver.location");
        long timeout = Long.parseLong(properties.getProperty("timeout.in.seconds"));
//        url = properties.getProperty("url");
        boolean isMaximizeWindow = Boolean.parseBoolean(properties.getProperty("maximize.window"));
        logger.debug("All properties assigned to variables");

        switch (browser) {
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

        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        if (isMaximizeWindow) {
            driver.manage().window().maximize();
        }

        return driver;
    }

    public void loadWebPage() {
        driver.get(url);
    }

    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
//            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            String testName = scenario.getName();
//            scenario.embed(screenshot, "image/png", testName);

//            ExtentReports extent = new ExtentReports();
//            ExtentTest test = extent.createTest(scenario.getName());
//            String screenshotName = scenario.getName().replaceAll(" ", "_"); //TODO Can be improved
//            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber/" + screenshotName + ".png");
//            try {
//                Files.copy(sourcePath, destinationPath);
//            } catch (IOException e) {
//                logger.error("[Files.copy]" + e.getMessage());
//            }
//            try {
//                test.addScreenCaptureFromPath(destinationPath.toString());
//            } catch (IOException e) {
//                logger.error("[test.add]" + e.getMessage());
//            }
        }
        driver.close();
    }
}
