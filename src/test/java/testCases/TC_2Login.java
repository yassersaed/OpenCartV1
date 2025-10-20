package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.RegisterAccountPage;
import testBase.BaseClass;

/**
 * Test Case: Login with Valid Credentials
 * Steps:
 1) Navigate to the application URL
 2) Navigate to 'My Account' and click 'Login'
 3) Perform login with valid cases
 4) Verify login by checking the presence of the 'My Account' page
 */
public class TC_2Login extends BaseClass {

    @Test(groups = { "Regression", "Master" })
    public void testLogin() {

        // 1) Navigate to the application URL
        String applicationURL1 = p.getProperty("appURL");
        driver.get(applicationURL1);
        logger.info("Navigated to application URL:" + applicationURL1);

        // 2) Navigate to 'My Account' and click 'Login'
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        logger.info("Clicked on 'My Account' link.");

        LoginPage login = homePage.clickLogin();
        logger.info("Clicked on 'Login' link, navigating to the Login Page.");

        // 3) Perform login with valid cases
        logger.info("Entering user data...");
        String emailAdd = p.getProperty("email");
        login.setEmailAddress(emailAdd);
        logger.info("Entered email: " + emailAdd);

        String password = p.getProperty("password");
        login.setPassword(password);
        logger.info("Entered password: ");

        login.clickLogin();
        logger.info("Clicked on the 'Login' button.");

        // 4) Verify login by checking the presence of the 'My Account' page
        MyAccountPage myAccount = new MyAccountPage(driver);
        logger.info("Verifying if the 'My Account' page is displayed...");

        Boolean accountPage = myAccount.isMyAccountPageExists();
        Assert.assertTrue(accountPage, "Login failed: MyAccount page not displayed");
        logger.info("Login successful. 'My Account' page is displayed.");
    }

}
