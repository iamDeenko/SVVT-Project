package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class SeleniumTest
{

    private WebDriver webDriver;
    private String baseURL;

    @BeforeEach
    public void setUp()
    {
        webDriver = new ChromeDriver();
        baseURL = "https://jysk.ba/";
        webDriver.get(baseURL);
    }

    @AfterEach
    public void tearDown() throws InterruptedException
    {
        Thread.sleep(1500);
        webDriver.close();
    }

    @Test
    public void testIfSeleniumWorks()
    {


        assertEquals("https://jysk.ba/", webDriver.getCurrentUrl());

    }


}