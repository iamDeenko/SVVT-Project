package project;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationLinkTest extends BaseTest
{

    @Test
    public void testNavigation() throws InterruptedException
    {
        String[] links = {
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
        // Loop through each item in the navbar.
        for (String link : links)
        {

            // Navigate to the webpage
            webDriver.get(appendBaseURL(link));


            acceptCookies();


            assertEquals(appendBaseURL(link), webDriver.getCurrentUrl());
        }
    }

}