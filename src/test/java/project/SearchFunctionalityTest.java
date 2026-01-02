package project;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchFunctionalityTest extends BaseTest
{

    @Test
    public void testValidInput() throws InterruptedException
    {
        webDriver.get(baseURL);

        acceptCookies();
        WebElement inputField = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div/div[1]/form/input"));

        inputField.sendKeys("stolica" + Keys.ENTER);

        String expectedURL = "https://jysk.ba/search?query=stolica&type=product";

        longWait();

        assertEquals(expectedURL, webDriver.getCurrentUrl());

    }

}
