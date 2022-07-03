//package com.utils;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.safari.SafariDriver;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class Browser {
//
//
//    public static WebDriver driver;
//    private static final String BROWSER = "browser";
//    private static final String ENV = "env";
//    private static String fileName = "";
//
//    Properties properties;
//
//
//    public void openBrowser() throws IOException {
//        Browsers browser;
//
//
//        if (System.getProperty(BROWSER) == null) {
//            browser = Browsers.CHROME;
//        } else {
//            browser = Browsers.browserForName(System.getProperty(BROWSER));
//        }
//        switch (browser) {
//            case CHROME:
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//                break;
//            case SAFARI:
//                WebDriverManager.safaridriver().setup();
//                driver = new SafariDriver();
//                break;
//            case FIREFOX:
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//                break;
//            case IE:
//                WebDriverManager.iedriver().setup();
//                driver = new InternetExplorerDriver();
//                break;
//            default:
//        }
//        getURL();
//
//
//    }
//
//    public void getURL() throws IOException {
//        String host = "";
//        if (System.getProperty(ENV) == null) {
//            host = "dev";
//        } else {
//            host = System.getProperty(ENV);
//        }
//
//        fileName = System.getProperty("user.dir") + "/src/test/java/com/properties/environment_%s.properties";
//
//        File file = new File(String.format(fileName, host));
//        properties = new Properties();
//        FileInputStream fileInputStream = null;
//        fileInputStream = new FileInputStream((file));
//        properties.load(fileInputStream);
//        driver.get(properties.getProperty("BASE_URL"));
//        driver.manage().window().maximize();
//    }
//
//    public void closeBrowser(){
//        driver.close();
//    }
//
//
//}
