package project;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationLoginTest extends BaseTest
{


    // Open website and click on the "Prijavi se" buttion
    @Test
    public void testValidLogin() throws InterruptedException
    {
        // Email and password I made via TempMail used for testing purposes.
        String email = "pikeceh610@gavrom.com";
        String password = "t528R3mTzSnu.eL";

        webDriver.get(appendBaseURL("customer/login"));

        acceptCookies();

        // Send the input
        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("password")).sendKeys(password);

        // Click on the submit button
        webDriver.findElement(By.xpath("//*[@id=\"jysk-customer-app\"]/div[4]/button[1]")).click();

        longWait();

        // If successful, the div should navigate us to /customer/dashboard
        assertEquals("https://jysk.ba/customer/dashboard", webDriver.getCurrentUrl());
    }


    @Test
    public void testInvalidLogin() throws InterruptedException
    {
        // Email and password are invalid and do not exist.
        String email = "pikeceh610@gavrom.com";
        String password = "blablabla";

        webDriver.get(appendBaseURL("customer/login"));

        acceptCookies();

        // Send the input
        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("password")).sendKeys(password);

        // Click on the submit button
        webDriver.findElement(By.xpath("//*[@id=\"jysk-customer-app\"]/div[4]/button[1]")).click();

        longWait();

        WebElement invalidLoginSpan = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/div/main/div[1]/form/div/div/div[1]/span"));
        String invalidLoginText = invalidLoginSpan.getText();

        assertEquals("Prijava neuspješna: e-adresa ili lozinka nisu ispravni", invalidLoginText);
    }



    @Test
    public void testMissingFields() throws InterruptedException
    {
        webDriver.get(appendBaseURL("customer/login"));

        acceptCookies();

        // Click on the submit button even tho no fields exist
        webDriver.findElement(By.xpath("//*[@id=\"jysk-customer-app\"]/div[4]/button[1]")).click();

        longWait();

        WebElement missingEmailSpan = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/div/main/div[1]/form/div/div/div[2]/span"));
        String missingEmailText = missingEmailSpan.getText();

        WebElement missingPasswordSpan = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/div/main/div[1]/form/div/div/div[3]/div[2]/span"));
        String missingPasswordText = missingPasswordSpan.getText();


        assertAll(()->{
            assertEquals("Polje je obavezno.", missingEmailText);
            assertEquals("Polje je obavezno.", missingPasswordText);
        });
    }

}
