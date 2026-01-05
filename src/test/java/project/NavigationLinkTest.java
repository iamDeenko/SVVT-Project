package project;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationLinkTest extends BaseTest
{

    // These are the links found in the navbar.

    private  String[] links = {
            "spavaca-soba",
            "spavaca-soba/madraci",
            "kupatilo",
            "radna-soba",
            "dnevna-soba",
            "trpezarija",
            "odlaganje",
            "zavjese",
            "basta",
            "kucanstvo",
            "nova-godina-bozic",
            "noviteti",
            "akcija",
            "trajno-niska-cijena",
            "outlet"
    };

    @Test
    public void testNavigation() throws InterruptedException
    {
        // Due to an ad popping up when I click on the navbar manually,
        // I have decided to take the links from the navbar and go to them one by one.

        // Loop through each item in the navbar.
        for (String link : this.links)
        {
            // Navigate to the webpage
            webDriver.get(appendBaseURL(link));

            acceptCookies();

            assertEquals(appendBaseURL(link), webDriver.getCurrentUrl());
        }
    }


    @Test
    public void testNonExistentNavigation() throws InterruptedException
    {
        // Open use the baseURL and append a non-existant URL to it.
        String imaginaryURL = "e3yhaha345";
        webDriver.get(appendBaseURL(imaginaryURL));

        acceptCookies();

        shortWait();

        // If the link does NOT exist, the page should display "Oops! Stranica koju ste trazili NE postoji"
        WebElement pageNotFoundText = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div[2]/div/div/div/section/article/div/div/h1"));
        String result = pageNotFoundText.getText();

        assertEquals("Oops! Stranica koju ste tražili ne postoji.", result);
    }

}