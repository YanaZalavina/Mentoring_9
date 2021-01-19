package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utils.UtilForDropdownList;

import java.util.ArrayList;

import static waits.Waits.waitToBeClickableForElement;
import static waits.Waits.waitToBeVisibleForElement;

public class GoogleCloudPricingCalculator extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();
    private String startPartOfPath = "//*[contains(text(), '";
    private String endPartOfPath = "')]";
    private String parentFrame = "//iframe[contains(@name, 'goog')]";
    private String childFrameTag = "iframe";
    private String xpathResultEstimateBlock = "//md-content[@ng-if='cloudCartCtrl.showComputeItems']";

    public GoogleCloudPricingCalculator(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@class='md-select-menu-container md-active md-clickable']//md-option/div")
    private WebElement elementFromDropdownList;
    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement googleCloudFrame;
    @FindBy(xpath = "//div[@class='md-toolbar-tools cpc-estimate-header']")
    private WebElement estimate;
    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesLabel;
    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInput;
    @FindBy(xpath = "//md-select-value[@id='select_value_label_59']//span[@class='md-select-icon']")
    private WebElement seriesDropdownButton;
    @FindBy(xpath = "//md-option[@value='n1']")
    private WebElement seriesN1;
    @FindBy(xpath = "//md-select-value[@id='select_value_label_60']//span[@class='md-select-icon']")
    private WebElement machineTypeDropdownButton;
    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement machineTypeN1Standard8;
    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement checkBoxAddGPUs;
    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuCount']//span[@class='md-select-icon']")
    private WebElement numberOfGPUsDropdownButton;
    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]'][@value='1']")
    private WebElement numberOfGPUsEquals1;
    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']//span[@class='md-select-icon']")
    private WebElement GPUTypeDropdownButton;
    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_V100']")
    private WebElement GPUTypeNVIDIATeslaV100;
    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.ssd']//span[@class='md-select-icon']")
    private WebElement localSSDDropdownButton;
    @FindBy(xpath = "//div[contains(text(), '2x375')]")
    private WebElement localSSD2x375GB ;
    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.location']//span[@class='md-select-icon']")
    private WebElement datacenterLocationDropdownButton;
    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='europe-west3']")
    private WebElement datacenterLocationFrankfurt;
    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.cud']//span[@class='md-select-icon']")
    private WebElement committedUsageDropdownButton;
    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//div[contains(text(), '1 Year')]")
    private WebElement committedUsage1Year;
    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimateButton;
    @FindBy(xpath = "//div[contains(text(), 'VM class')]")
    private WebElement VMClassField;
    @FindBy(xpath = "//button[@id='email_quote']")
    private WebElement emailEstimateButton;
    @FindBy(xpath = "//input[@ng-model='emailQuote.user.email']")
    private WebElement emailInput;
    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;
    @FindBy(xpath = "//div[text()='Compute Engine']")
    private WebElement computerEngineLink;
    @FindBy(xpath = "//button[@ng-click='cloudCartCtrl.removeCartItem(item)']")
    private WebElement deleteEstimateButton;

    private String createXPath(String valueOfText){
        return startPartOfPath
                +valueOfText
                +endPartOfPath;
    }

    public GoogleCloudPricingCalculator loadPage(){
        driver.switchTo().frame(driver.findElement(By.xpath(parentFrame)));
        driver.switchTo().frame(driver.findElement(By.tagName(childFrameTag)));
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(estimate));
        logger.info("GoogleCloudPricingCalculator page loaded");
        return this;
    }
    public GoogleCloudPricingCalculator enterNumberOfInstances(String numberOfInstances){
        numberOfInstancesLabel.click();
        new Actions(driver).sendKeys(numberOfInstancesLabel, numberOfInstances).build().perform();
        logger.info("Entered number of instances: " + numberOfInstances);
        //numberOfInstancesInput.sendKeys(numberOfInstances);
        return this;
    }

    public GoogleCloudPricingCalculator selectSeries(String selectedSeries){
        waitToBeVisibleForElement(driver, seriesDropdownButton);
        new UtilForDropdownList().findAndClickOnElement(driver, seriesDropdownButton);
        waitToBeClickableForElement(driver, elementFromDropdownList);
        new UtilForDropdownList().findAndClickOnElementFromDropDownList(driver, selectedSeries);
        logger.info("Selected series: " + selectedSeries);
        return this;
    }

    public GoogleCloudPricingCalculator selectMachineType(String selectedMachineType){
        //machineTypeDropdownButton.click();
        waitToBeVisibleForElement(driver, machineTypeDropdownButton);
        new UtilForDropdownList().findAndClickOnElement(driver, machineTypeDropdownButton);
        waitToBeClickableForElement(driver, elementFromDropdownList);
        new UtilForDropdownList().findAndClickOnElementFromDropDownList(driver, selectedMachineType);
        logger.info("Selected machine type: " + selectedMachineType);
        return this;
    }

    public GoogleCloudPricingCalculator activateCheckBoxAddGPUs() {
        waitToBeClickableForElement(driver, checkBoxAddGPUs);
        //checkBoxAddGPUs.click();
        new UtilForDropdownList().findAndClickOnElement(driver, checkBoxAddGPUs);
        logger.info("Check-box AddGPUs activated");
        return this;
    }

    public GoogleCloudPricingCalculator enterNumberOfGPUs(String enteredNumberOfGPUs) {
        //numberOfGPUsDropdownButton.click();
        waitToBeVisibleForElement(driver, numberOfGPUsDropdownButton);
        new UtilForDropdownList().findAndClickOnElement(driver, numberOfGPUsDropdownButton);
        waitToBeClickableForElement(driver, elementFromDropdownList);
        new UtilForDropdownList().findAndClickOnElementFromDropDownList(driver, enteredNumberOfGPUs);
        logger.info("Entered number of GPUs: " + enteredNumberOfGPUs);
        return this;
    }

    public GoogleCloudPricingCalculator selectGPUType(String selectedGPUType) {
        //GPUTypeDropdownButton.click();
        waitToBeVisibleForElement(driver, GPUTypeDropdownButton);
        new UtilForDropdownList().findAndClickOnElement(driver, GPUTypeDropdownButton);
        waitToBeClickableForElement(driver, elementFromDropdownList);
        new UtilForDropdownList().findAndClickOnElementFromDropDownList(driver, selectedGPUType);
        logger.info("Selected GPU Type: " + selectedGPUType);
        return this;
    }


    public GoogleCloudPricingCalculator selectLocalSSD(String selectedLocalSSD){
        //localSSDDropdownButton.click();
        waitToBeVisibleForElement(driver, localSSDDropdownButton);
        new UtilForDropdownList().findAndClickOnElement(driver, localSSDDropdownButton);
        waitToBeClickableForElement(driver, elementFromDropdownList);
        new UtilForDropdownList().findAndClickOnElementFromDropDownList(driver, selectedLocalSSD);
        logger.info("Selected Local SSD: " + selectedLocalSSD);
        return this;
    }


    public GoogleCloudPricingCalculator selectDatacenterLocation(String selectedDatacenterLocation){
        //datacenterLocationDropdownButton.click();
        waitToBeVisibleForElement(driver, datacenterLocationDropdownButton);
        new UtilForDropdownList().findAndClickOnElement(driver, datacenterLocationDropdownButton);
        waitToBeClickableForElement(driver, elementFromDropdownList);
        new UtilForDropdownList().findAndClickOnElementFromDropDownList(driver, selectedDatacenterLocation);
        logger.info("Selected Datacenter Location: " + selectedDatacenterLocation);
        return this;
    }


    public GoogleCloudPricingCalculator selectCommittedUsage(String selectedCommittedUsage){
        //committedUsageDropdownButton.click();
        waitToBeVisibleForElement(driver, committedUsageDropdownButton);
        new UtilForDropdownList().findAndClickOnElement(driver, committedUsageDropdownButton);
        waitToBeClickableForElement(driver, elementFromDropdownList);
        new UtilForDropdownList().findAndClickOnElementFromDropDownList(driver, selectedCommittedUsage);
        logger.info("Selected Committed Usage: " + selectedCommittedUsage);
        return this;
    }


    public GoogleCloudPricingCalculator clickOnAddToEstimate(){
        waitToBeVisibleForElement(driver, addToEstimateButton);
        new UtilForDropdownList().findAndClickOnElement(driver, addToEstimateButton);
        logger.info("Added To Estimate");
        //addToEstimateButton.click();
        return this;
    }

    public GoogleCloudPricingCalculator clickOnEmailEstimateButton(){
        waitToBeVisibleForElement(driver, emailEstimateButton);
        new UtilForDropdownList().findAndClickOnElement(driver, emailEstimateButton);
        logger.info("Email estimate window opened");
        //emailEstimateButton.click();
        return this;
    }

    public GoogleCloudPricingCalculator enterCopiedEmail(){
        waitToBeClickableForElement(driver, emailInput);
        emailInput.click();
        new UtilForDropdownList().findAndClickOnElement(driver, emailInput);
        emailInput.sendKeys(Keys.CONTROL, "v");
        logger.info("Entered copied email");
        return this;
    }

    public GoogleCloudPricingCalculator clickOnSendEmailButton(){
        //sendEmailButton.click();
        waitToBeVisibleForElement(driver, sendEmailButton);
        new UtilForDropdownList().findAndClickOnElement(driver, sendEmailButton);
        logger.info("Sent email with estimation");
        return this;
    }

    public Boolean elementLocatedOnPage (String visibleNameOfWebElement){
        logger.info("Checking presence of webElement "+visibleNameOfWebElement+" on page");
        return driver.findElement(By.xpath(createXPath(visibleNameOfWebElement))).isDisplayed();
    }

    public GoogleCloudPricingCalculator clickOnDeleteEstimateButton(){
        //deleteEstimateButton.click();
        waitToBeVisibleForElement(driver, deleteEstimateButton);
        new UtilForDropdownList().findAndClickOnElement(driver, deleteEstimateButton);
        logger.info("Estimation deleted");
        return this;
    }

    public int countNumberOfElementsEstimateResults(){
        logger.info("Counting number of elements Estimate Results");
        return driver.findElements(By.xpath(xpathResultEstimateBlock)).size();
    }

    public String getWebElementText(String visibleNameOfWebElement){
        WebElement element = driver.findElement(By.xpath(createXPath(visibleNameOfWebElement)));
        waitToBeClickableForElement(driver, element);
        logger.info("Got text from webElement "+visibleNameOfWebElement);
        return element.getText().trim();
    }

    public String getURL(){
        logger.info("Getting URL");
        return ((JavascriptExecutor)driver).executeScript("return document.URL;").toString();
    }

    public GoogleCloudPricingCalculator openNewTab(ArrayList<String> tabs){
        new UtilForDropdownList().switchingBetweenTwoTabsAndOpenNewTab(driver, tabs);
        return this;
    }

    public GoogleCloudPricingCalculator openSecondTab(ArrayList<String> tabs){
        new UtilForDropdownList().switchToNextTab(driver, tabs);
        return this;
    }

    public GoogleCloudPricingCalculator openFirstTab(ArrayList<String> tabs){
        new UtilForDropdownList().switchToPreviousTab(driver, tabs);
        return this;
    }

}
