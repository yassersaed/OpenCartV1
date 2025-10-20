package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchResultsPage;
import testBase.BaseClass;

/**
 * Test Case: Add Product to Cart
 * 1. Navigate to application URL
 * 2. Enter an existing product name in the search text box
 * 3. Click the search button
 * 4. Verify the product appears in the search results
 * 5. Select the product from the search results
 * 6. Set the desired quantity
 * 7. Add the product to the cart
 * 8. Verify the success message
 */

public class TC5_AddToCart extends BaseClass {

    @Test(groups = { "Regression", "Master" })
    public void testAddToCart(){

        // 1) Navigate to application URL
        String appURL = p.getProperty("appURL");
        driver.get(appURL);
        logger.info("Navigated to application URL: " + appURL);

        // Initialize the HomePage
        HomePage homePage = new HomePage(driver);
        String productName = p.getProperty("searchProductName");

        // 2)Enter the product name in the search text box
        homePage.enterProductName(productName);
        logger.info("Entered product name in the search box: " + productName);

        // 3) search for product
        SearchResultsPage searchResultsPage = homePage.clickSearch();
        logger.info("Clicked the Search button.");

        // 4) Verify the product exists in search results
        if (searchResultsPage.isProductExist(productName)) {
            logger.info("Product found in search results: " + productName);

            // 5) Select the product from the search results
            ProductPage productPage = searchResultsPage.selectProduct(productName);
            logger.info("Selected product: " + productName);

            // 6) Set the desired quantity
            productPage.setQuantity(p.getProperty("productQuantity"));
            logger.info("Set quantity to: " + p.getProperty("productQuantity"));

            // 7) Add the product to the cart
            productPage.addToCart();
            logger.info("Clicked 'Add to Cart' for product: " + productName);

            // 8) Verify the success message
            boolean isSuccessMessageDisplayed = productPage.checkConfMsg();
            Assert.assertTrue(isSuccessMessageDisplayed, "Success message not displayed!");
            logger.info("Success message verified successfully.");
        } else {
            logger.error("Product not found in search results: " + productName);
            Assert.fail("Product not found in search results.");
        }
    }
}
