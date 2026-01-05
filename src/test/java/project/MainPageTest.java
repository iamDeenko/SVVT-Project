package project;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends BaseTest
{

    @Test
    public void mainPageTest()
    {
        webDriver.get(baseURL);

        acceptCookies();

        // If the website works correctly we should be able to click on the "JYSK" brand icon successfuly on load.
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[1]/div[1]/div/a/img")).click();


        // If the website works and if it redirects succesfully, the URL should be the BaseURL from the BaseTest class.
        assertEquals(baseURL, webDriver.getCurrentUrl());
    }



    @Test
    public void testNavbarOpensAndCloses() throws InterruptedException {
        webDriver.get(baseURL);

        int successfulClickCount = 0;

        acceptCookies();

        // 1. Find the button
        WebElement navbarButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[1]/div[2]/a/span"));

        // 2. Check if visible, then click and increment
        if (navbarButton.isDisplayed()) {
            navbarButton.click();
            successfulClickCount += 1;
        }

        shortWait();

        // 3. Find the close button
        WebElement closeNavbarButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div/div/div[2]/div/div[1]/div/button"));

        // 4. Check if visible, then click and increment
        if (closeNavbarButton.isDisplayed()) {
            closeNavbarButton.click();
            successfulClickCount += 1;
        }

        assertEquals(2, successfulClickCount);
    }



    @Test
    public void testCustomerServiceLink()
    {
        webDriver.get(baseURL);

        acceptCookies();

        // Clicks on the customer service anchor tag
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/header/div[2]/div/div/div/div[2]/div/ul/li[4]/a/span")).click();

        // Checks if the URL is "https://jysk.ba/customer-service" we have redirected succesfully.
        assertEquals("https://jysk.ba/customer-service", webDriver.getCurrentUrl());
    }

}
