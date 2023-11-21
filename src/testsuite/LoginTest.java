package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 1. userShouldNavigateToLoginPageSuccessfully * click on the ‘Login’ link
 * * Verify the text ‘Welcome, Please Sign In!’
 * 2. userShouldLoginSuccessfullyWithValidCredentials
 * * click on the ‘Login’ link
 * * Enter valid username
 * * Enter valid password
 * * Click on ‘LOGIN’ button
 * * Verify the ‘Log out’ text is display
 * 3. verifyTheErrorMessage
 * * click on the ‘Login’ link
 * * Enter invalid username
 * * Enter invalid password
 * * Click on Login button
 * * Verify the error message ‘Login was unsuccessful.
 * Please correct the errors and try again. No customer account found’
 */
public class LoginTest extends BaseTest {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        //Find the login link and click on login link
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();
        String expectedText = "Welcome, Please Sign In!";
        // Find the actual text element and get the text from element
        WebElement actualTextElement = driver.findElement(By.xpath("//h1"));
        String actualText = actualTextElement.getText();
        // Verify expected and actual text
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        WebElement loginlink = driver.findElement(By.linkText("Log in"));
        loginlink.click();

        //Find the Email field type the Email address to email field
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("dhara01@yahoo.co.uk");

        //Find the password field type the password to password field
        WebElement passwordField = driver.findElement(By.name("Password"));
        passwordField.sendKeys("testing123");

        //Find the login button element and click
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

        String expectedText = "Welcome to our store";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals(expectedText, actualText);


    }

    @Test
    public void verifyTheErrorMessage() {
        WebElement loginlink = driver.findElement(By.linkText("Log in"));
        loginlink.click();

        //Find the Email field type the Email address to email field
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("dhara01@yahoo.co.uk");

        //Find the password field type the password to password field
        WebElement passwordField = driver.findElement(By.name("Password"));
        passwordField.sendKeys("Dhara123");

        //Find the login button element and click
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found";

        //Find the errorMessage element and get the text
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']")).getText();
        Assert.assertEquals("Test case pass",expectedErrorMessage, actualErrorMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();

    }
}
