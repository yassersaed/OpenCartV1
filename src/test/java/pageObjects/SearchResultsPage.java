package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // WebElement for the search results page header
    @FindBy(xpath = "//div[@id='content']/h1")
    WebElement searchPageHeader;

    // WebElements for the product images in the search results
    @FindBy(xpath = "//*[@id='content']/div[3]//img")
    List<WebElement> searchProducts;

    public boolean isSearchResultsPageExists() {
        try {
            return searchPageHeader.getText().contains("Search -");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductExist(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(searchProducts)); // Wait for product list to be visible
            // Loop through products to find a match by name
            for (WebElement product : searchProducts) {
                if (product.getAttribute("title").equals(productName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error checking product existence: " + e.getMessage());
        }
        return false;
    }

    public ProductPage selectProduct(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(searchProducts)); // Wait for product list to be visible
            // Loop through products to find the matching product and click it
            for (WebElement product : searchProducts) {
                if (product.getAttribute("title").equals(productName)) {
                    wait.until(ExpectedConditions.elementToBeClickable(product)).click(); // Wait for the product to be clickable
                    return new ProductPage(driver);
                }
            }
            System.out.println("Product not found: " + productName);
        } catch (Exception e) {
            System.out.println("Error selecting product: " + e.getMessage());
        }
        return null;
    }
}
