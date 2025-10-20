package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Page Elements

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyaccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;

    @FindBy(linkText = "Login")
    WebElement lnkLogin;

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement txtSearchbox;

    @FindBy(xpath = "//div[@id='search']//button[@type='button']")
    WebElement btnSearch;

    // Actions

    public boolean isHomePageExists() {
        try {
            return driver.getTitle().equals("Your Store");

        } catch (Exception e) {
            return false;
        }
    }

    // Click "My Account" link
    public void clickMyAccount() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lnkMyaccount)).click();
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking 'My Account': " + e.getMessage());
        }
    }

    public RegisterAccountPage clickRegister() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lnkRegister)).click();
            return new RegisterAccountPage(driver);
        } catch (Exception e) {
            System.out.println("Exception while clicking 'Register': " + e.getMessage());
            return null;
        }
    }

    public LoginPage clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lnkLogin)).click();
            return new LoginPage(driver);
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking 'Login': " + e.getMessage());
            return null;
        }
    }

    public void enterProductName(String pName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtSearchbox)).sendKeys(pName);
        } catch (Exception e) {
            System.out.println("Exception while entering product name: " + e.getMessage());
        }
    }

    public SearchResultsPage clickSearch() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
            return new SearchResultsPage(driver);
        } catch (Exception e) {
            System.out.println("Exception while clicking 'Search': " + e.getMessage());
            return null;
        }
    }
}
