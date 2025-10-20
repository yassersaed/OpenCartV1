package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchResultsPage;
import testBase.BaseClass;

/**
 * Test Case: Product Search
 * Steps:
 * 1) Navigate to the application URL
 * 2) Navigate to the Home page and initiate product search
 * 3) Enter the product name in the search field
 * 4) Click on the search button
 * 5) Verify if the product is displayed in the search results
 */

public class TC_4SearchProduct extends BaseClass {

    @Test(groups = { "Regression", "Master" })
    public void testSearchProduct(){

        //1) Navigate to the application URL
        String applicationUrl = p.getProperty("appURL");
        driver.get(applicationUrl);
        logger.info("Navigated to application URL: " + applicationUrl);

        // Step 2: Navigate to the Home page and initiate product search
        HomePage homePage = new HomePage(driver);
        logger.info("Navigated to the Home page.");

        // Step 3: Enter the product name in the search field
        String product = "mac"; // Could be parameterized as needed
        homePage.enterProductName(product);
        logger.info("Entered product name in search field: " + product);

        // Step 4: Click on the search button
        SearchResultsPage searchResults =homePage.clickSearch();
        logger.info("Clicked on the 'Search' button to initiate product search.");

        // Step 5: Verify if the product is displayed in the search results
        Assert.assertTrue(searchResults.isSearchResultsPageExists());
        logger.info("Navigated to the Search Results page.");

        // Check if the specific product exists in the search results
        String expectedProductName = "MacBook";
        boolean isProductDisplayed = searchResults.isProductExist(expectedProductName);
        logger.info("Verifying if the product '" + expectedProductName + "' is displayed in the search results.");

        // Assertion to verify that the product exists in the search results
        Assert.assertTrue(isProductDisplayed, "Product '" + expectedProductName + "' not found in search results.");
        logger.info("Product '" + expectedProductName + "' found in search results. Test passed.");
    }
}
