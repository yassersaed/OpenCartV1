package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Page Elements

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmailAddress;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    public void setEmailAddress(String email){
        try{
            wait.until(ExpectedConditions.visibilityOf(txtEmailAddress));
            txtEmailAddress.clear();
            txtEmailAddress.sendKeys(email);
        }catch (Exception e){
            System.out.println("Email field not visible: " + e.getMessage());
        }
    }

    public void setPassword(String pwd) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtPassword)); // Wait for password field to be visible
            txtPassword.clear(); // Clear the field before entering text
            txtPassword.sendKeys(pwd);
        } catch (Exception e) {
            System.out.println("Password field not visible: " + e.getMessage());
        }
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnLogin)); // Wait for the login button to be clickable
            btnLogin.click();
        } catch (Exception e) {
            System.out.println("Login button not clickable: " + e.getMessage());
        }
    }
}
