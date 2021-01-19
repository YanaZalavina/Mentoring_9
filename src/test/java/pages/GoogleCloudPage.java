package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UtilForDropdownList;

public class GoogleCloudPage extends AbstractPage{
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    //private final Logger logger = LogManager.getRootLogger();
    private final Logger logger = LogManager.getLogger(GoogleCloudPage.class);

    public GoogleCloudPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchButton;

    public GoogleCloudPage openPage() {
        System.out.println("openPage()");
        //driver.navigate().to(HOMEPAGE_URL);
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label='Search']")));
        return this;
    }

    public GoogleCloudPage enterValueForSearching(String valueForSearching){
       // new Actions(driver).click(searchButton).build().perform();

        //searchButton.click();
        new UtilForDropdownList().findAndClickOnElement(driver, searchButton);
        System.out.println("click on button");
        searchButton.sendKeys(valueForSearching);
        logger.info("Entered value for searching: " + valueForSearching);
        return this;
    }

    /*public SearchResultPage pressEnter(){
        searchButton.sendKeys(Keys.ENTER);
        System.out.println("pressEnter()");
        return new SearchResultPage(driver);
    }*/

    public GoogleCloudPage pressEnter(){
        searchButton.sendKeys(Keys.ENTER);
        System.out.println("pressEnter()");
        logger.info("GoogleCloudPricingCalculator page opened");
        return this;
    }
}
