package testhelpers.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.HomePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    public WebDriver initialiseWebDriver() throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "/src/config-awsomeqa.properties");
        properties.load(fis);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));//full screen


        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
    @BeforeMethod(alwaysRun=true)
    public HomePage launchHomePage() throws IOException {
        driver = initialiseWebDriver();
        homePage = new HomePage(driver);
        homePage.goTo();
        return homePage;

    }
    @AfterMethod(alwaysRun=true)
    public void tearDown() {
       driver.close();

    }



}