package pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Page Elements
    @FindBy(xpath = "//h1[text() = 'Account Logout']")
    WebElement msgTitle;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement btnContinue;

    public Boolean isLogoutPageOpened(){
        try {
            wait.until(ExpectedConditions.visibilityOf(msgTitle)).isDisplayed();
            return true;
        }catch (Exception e){
            System.out.println("Logout Page Title not found: " + e.getMessage());
            return false;        }
    }

    public HomePage clickContinue() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnContinue)); // Wait for the login button to be
            // clickable
            btnContinue.click();
            return new HomePage(driver);
        } catch (TimeoutException e) {
            System.out.println("Continue button not clickable: " + e.getMessage());
            return null;
        }
    }


}
