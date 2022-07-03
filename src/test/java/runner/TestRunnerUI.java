package runner;

import com.utils.Browsers;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@CucumberOptions(
        monochrome = true,
        features = {"src/test/resources/"},
        glue = "com.stepDefs",
        plugin = "json:target/cucumber-reports/CucumberTestReport.json",
        tags = "@ui"
)

public class TestRunnerUI extends AbstractTestNGCucumberTests {

    public static WebDriver driver;
    private static final String BROWSER = "browser";
    private static final String ENV = "env";
    private static String fileName = "";

    Properties properties;


    public static Properties config = null;

    public void LoadConfigProperty() throws IOException {
        config = new Properties();
        FileInputStream ip = new FileInputStream(
                System.getProperty("user.dir") + "//src//test//resources//config//config.properties");
        config.load(ip);
    }

    public void openBrowser() throws Exception {
        Browsers browser;
        browser = Browsers.browserForName(System.getProperty(BROWSER));

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            case FIREFOX:
                String pathFF = System.getProperty("user.dir") + "/src/main/java/drivers/geckodriver";
                System.setProperty("webdriver.gecko.driver", pathFF);
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case IE:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
        }

        getURL();
    }

    public void getURL() throws IOException {
        String host = "";
        if (System.getProperty(ENV) == null) {
            host = "dev";
        } else {
            host = System.getProperty(ENV);
        }

        fileName = System.getProperty("user.dir") + "/src/test/java/com/properties/environment_%s.properties";

        File file = new File(String.format(fileName, host));
        properties = new Properties();
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream((file));
        properties.load(fileInputStream);
        driver.get(properties.getProperty("BASE_URL"));
        driver.manage().window().maximize();
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        openBrowser();
    }

    @AfterSuite(alwaysRun = true)
    public void generateHTMLReports() {
        reportGeneration.generateCucumberReport();
    }

    @AfterSuite(alwaysRun = true)
    public void quit() throws IOException, InterruptedException {
        driver.quit();
    }
}