package project;

import base.BaseTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class NewsletterValidationTest extends BaseTest
{

    @Order(1)
    @Test
    public void testInvalidEmailFormatValidation() throws InterruptedException
    {
        // Open the browser window
        webDriver.get(baseURL);


        acceptCookies();


        // Scroll to the name input field and input our name
        WebElement nameInputField = webDriver.findElement(By.id("newsletter-firstname"));
        scrollAndClick(nameInputField);
        nameInputField.sendKeys("Test Testinovich");

        shortWait();

        //Input an invalid email
        webDriver.findElement(By.id("teradata-email")).sendKeys("bh3@c.c");

        shortWait();

        // Click the "Accept TOS button"
        webDriver.findElement(By.id("edit-terms")).click();

        shortWait();

        // Continue to send the newsletter?
        webDriver.findElement(By.id("edit-terms")).click();


        // If the email is incorrect a little notification saying that we need a correct email address will pop up below the input field
        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[5]/div/div/form/div[1]/div[2]/div[2]"));

        // Get the value from the webelement and compare it. It should be "Molimo unesite važeću E-mail adresu.".
        String value = webElement.getText();
        String expectedValue = "Molimo unesite važeću E-mail adresu.";
        assertEquals(expectedValue, value);
    }


    @Order(2)
    @Test
    public void testValidEmailFormatValidation() throws InterruptedException
    {
        // Open the browser window
        webDriver.get(baseURL);



        acceptCookies();


        // Scroll to the name input field and input our name
        WebElement nameInputField = webDriver.findElement(By.id("newsletter-firstname"));
        scrollAndClick(nameInputField);
        nameInputField.sendKeys("Test Testinovich");

        shortWait();

        //Input an invalid email
        webDriver.findElement(By.id("teradata-email")).sendKeys("test@gmail.com");

        shortWait();

        // Click the "Accept TOS button"
        webDriver.findElement(By.id("edit-terms")).click();

        shortWait();

        // Continue to send the newsletter?
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[5]/div/div/form/div[1]/div[3]/button")).click();


        // If the email is incorrect a little notification saying that we need a correct email address will pop up below the input field
        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[5]/div/div/form/div[1]/h2"));

        // Get the value from the webelement and compare it. It should be "Hvala vam na zanimanju za naše Novosti.".
        String value = webElement.getText();
        String expectedValue = "Hvala vam na zanimanju za naše Novosti.";
        assertEquals(expectedValue, value);
    }
}
