package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterAccountPage;
import testBase.BaseClass;

/**
 * Test Case: Create an Account
 *
 * Steps:
 * 1) Navigate to application URL
 * 2) Navigate to 'My Account' and click 'Register'
 * 3) Fill in registration details
 * 4) Agree to Privacy Policy and submit registration
 * 5) Validate confirmation message
 */

public class TC_1CreateAccount extends BaseClass {

    @Test(groups = { "Regression", "Master" })
    public void testCreateAccount (){

        //1) Navigate to application URL
        String applicationURL = p.getProperty("appURL");
        driver.get(applicationURL);
        logger.info("Navigated to application URL:" + applicationURL);

        //2) Navigate to 'My Account' and click 'Register'
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        logger.info("Clicked on MyAccount link.");

        RegisterAccountPage RegisterAccount = homePage.clickRegister();
        logger.info("Clicked on Register link, navigated to Registration page.");

        //3) Fill in registration details
        logger.info("Entering user details...");
        RegisterAccount.setFirstName(randomeString().toUpperCase());
        logger.info("First Name set successfully...");

        RegisterAccount.setLastName(randomeString().toUpperCase());
        logger.info("Last Name set successfully...");

        String userEmail = randomeString() + "@gmail.com";
        RegisterAccount.setEmail(userEmail);
        logger.info("Email entered:" + userEmail);

        String userPhoneNumber = randomeNumber();
        RegisterAccount.setTelephone(userPhoneNumber);
        logger.info("Entered phone number:" + userPhoneNumber);

        String userPassword = randomeAlphaNumberic();

        RegisterAccount.setPassword(userPassword);
        RegisterAccount.setConfirmPassword(userPassword);
        logger.info("Entered password and confirmed password successfully.");

        // 4) Agree to Privacy Policy and submit registration
        RegisterAccount.setPrivacyPolicy();
        logger.info("Agreed to Privacy Policy.");

        RegisterAccount.clickContinue();
        logger.info("Clicked 'Continue' to submit the registration form.");

        // 5) Validate confirmation message
        logger.info("Validating the confirmation message...");
        String confirmationMessage = RegisterAccount.getConfirmationMsg();
        Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!",
                "Registration failed: Confirmation message mismatch.");
        logger.info("Account registration successful. Confirmation message validated.");


    }
}
