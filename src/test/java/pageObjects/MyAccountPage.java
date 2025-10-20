package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Page Elements

    @FindBy(xpath = "//h2[text()='My Account']") // MyAccount Page heading
    WebElement msgTitle;

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']") // Logout link
    WebElement btnLogout;

    // Method to validate if My Account page Title is displayed
    public boolean isMyAccountPageExists() {
        try {
            wait.until(ExpectedConditions.visibilityOf(msgTitle)).isDisplayed(); // Wait for title to be visible
            return true;
        } catch (Exception e) {
            System.out.println("My Account page Title not found: " + e.getMessage());
            return false;
        }
    }

    public LogoutPage clickLogout() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnLogout)).click(); // Wait for the logout link to be clickable
            return new LogoutPage(driver);
        } catch (Exception e) {
            System.out.println("Unable to click Logout link: " + e.getMessage());
            return null;
        }
    }


}
