package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import org.testng.asserts.SoftAssert;


/**
 * Test Case: User Logout
 *
 * Steps:
 * 1) Navigate to the application URL
 * 2) Navigate to the Login page from the Home page
 * 3) Perform login with valid data
 * 4) Verify login by checking the presence of the 'My Account' page
 * 5) Click on Logout link
 * 6) Click on Continue button
 * 7) Verify navigation back to the Home Page
 */
public class TC_3Logout extends BaseClass {

    @Test(groups = { "Regression", "Master" })
    public void testLogout(){

        // Step 1: Navigate to the application URL
        String applicationUrl = p.getProperty("appURL");
        driver.get(applicationUrl);
        logger.info("Navigated to application URL: " + applicationUrl);

        // Step 2: Navigate to the Login page from the Home page
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        logger.info("Clicked on 'My Account' link.");

        LoginPage loginPage = homePage.clickLogin();
        logger.info("Clicked on 'Login' link, navigating to the Login Page.");

        // Step 3: Perform login with valid data
        String Email = p.getProperty("email");
        String Password = p.getProperty("password");
        logger.info("Entering login credentials...");
        loginPage.setEmailAddress(Email);
        logger.info("Entered email: " + Email);

        loginPage.setPassword(Password);
        logger.info("Entered password.");

        loginPage.clickLogin();
        logger.info("Clicked on the 'Login' button.");

        // Step 4: Verify login by checking the presence of the 'My Account' page
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        logger.info("Checking if 'My Account' page is displayed...");
        boolean isMyAccountPageDisplayed = myAccountPage.isMyAccountPageExists();
        Assert.assertTrue(isMyAccountPageDisplayed, "Login failed: MyAccount page not displayed");
        logger.info("Login successful. 'My Account' page is displayed.");

        // Step 5: Click on Logout link
        logger.info("Logging out from the account...");
        LogoutPage logoutPage = myAccountPage.clickLogout();
        logger.info("Clicked on 'Logout' link.");

        // Step 6: Click on Continue button
        logger.info("Clicking on 'Continue' button to navigate back to the Home Page...");
        HomePage postLogoutHomePage = logoutPage.clickContinue();

        // Step 7: Verify navigation back to the Home Page
        logger.info("Checking if the Home Page is displayed after logout...");
        boolean isHomePageVisible = postLogoutHomePage.isHomePageExists();
        Assert.assertTrue(isHomePageVisible, "Logout failed: Home Page is not displayed.");
        logger.info("Logout successful. Navigated back to the Home Page.");
    }
}
