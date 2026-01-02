package project;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationLoginTest extends BaseTest
{


    // Open website and click on the "Prijavi se" buttion
    @Test
    public void testValidLogin() throws InterruptedException
    {

        // Email and password I made via tempmail used for testing purposes.

        String email = "pikeceh610@gavrom.com";
        String password = "t528R3mTzSnu.eL";


        webDriver.get(appendBaseURL("customer/login"));

        acceptCookies();


        // Send the input
        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("password")).sendKeys(password);

        // Click on the submit button
        webDriver.findElement(By.xpath("//*[@id=\"jysk-customer-app\"]/div[4]/button[1]")).click();


        Thread.sleep(2500);

        // If successful, the div should navigate us to /customer/dashboard


        String expectedURL = "https://jysk.ba/customer/dashboard";

        assertEquals(expectedURL, webDriver.getCurrentUrl());

    }

}
