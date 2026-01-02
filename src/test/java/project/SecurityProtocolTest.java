package project;

import base.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityProtocolTest extends BaseTest
{


    @Test
    public void testHTTPRedirection() throws InterruptedException
    {
        // Get the HTTP version of the site.
        webDriver.get("http://jysk.ba/");


        shortWait();

        // If the redirection works, the URL should be the URL below.
        String expectedURL = "https://jysk.ba/";
        assertEquals(expectedURL, webDriver.getCurrentUrl());
    }
}
