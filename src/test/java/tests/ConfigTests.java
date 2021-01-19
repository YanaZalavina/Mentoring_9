package tests;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import utils.TestListener;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public class ConfigTests {
    protected WebDriver driver;
    //String FIRST_NODE = "http://192.168.100.7:5557/wd/hub";
    //String SECOND_NODE = "http://192.168.100.7:5558/wd/hub";

    //@BeforeClass(alwaysRun = true)
    //@Parameters("myBrowser")
    //public void browserSetUp(String myBrowser) throws MalformedURLException {
    @BeforeMethod()
        public void browserSetUp() {
            System.out.println("browserSetUp()");
        /*if(myBrowser.equals("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.setCapability("platform", "WIN10");
            options.setCapability("platformName", "windows");
            driver = new RemoteWebDriver(new URL(FIRST_NODE), options);
        }
        else if(myBrowser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("platform", "WIN10");
            options.setCapability("platformName", "windows");
            driver = new RemoteWebDriver(new URL(SECOND_NODE), options);
        }


        driver.manage().window().maximize();*/
        driver = DriverSingleton.getDriver();
    }
   // @AfterClass(alwaysRun = true)
   @AfterMethod(alwaysRun = true)
    public void browserQuit(){
        //driver.quit();
        //driver = null;
        DriverSingleton.closeDriver();
    }
}
