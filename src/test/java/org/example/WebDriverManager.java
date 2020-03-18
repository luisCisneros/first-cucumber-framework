package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    private static final Logger logger = LogManager.getLogger();
    private String url;

    public String getUrl() {
        return url;
    }

    public WebDriver setUpDriver(WebDriver driver) { // Considerar declarar WebDriver dentro del metodo en lugar de recibirlo como parametro
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
        url = properties.getProperty("url");
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
        }
        logger.info("{} browser will be used for testing", browser);

        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        if (isMaximizeWindow) {
            driver.manage().window().maximize();
        }

        return driver;
    }
}
