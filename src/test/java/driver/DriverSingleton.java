package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSingleton {
    //private static final String RESOURCES_PATH = "src\\test\\java\\resources\\";
   private static WebDriver driver;

   //change it to use properties
    private static String FIRST_NODE = "http://192.168.100.7:5557/wd/hub";
    private static String SECOND_NODE = "http://192.168.100.7:5558/wd/hub";


    private DriverSingleton(){}

    public static WebDriver getDriver() {
       // driver = new ChromeDriver();
        System.out.println("getDriver()");
        if (null == driver) {
            //driver = new ChromeDriver();
            System.out.println("getDriver()");
            switch (System.getProperty("browser")){
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    System.out.println("firefox");
                    driver = new FirefoxDriver();
                   /* FirefoxOptions options = new FirefoxOptions();
                    options.setCapability("platform", "WIN10");
                    options.setCapability("platformName", "windows");
                    driver = new RemoteWebDriver(new URL(SECOND_NODE), options);*/
                   break;
                }
                default: {
                    System.out.println("select_Chrome");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();


                    /*ChromeOptions options = new ChromeOptions();
                    options.setCapability("platform", "WIN10");
                    options.setCapability("platformName", "windows");
                    driver = new RemoteWebDriver(new URL(FIRST_NODE), options);*/
                    break;
                }
        }
            driver.manage().window().maximize();
        }
        return driver;
    }

  public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
