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


    @Test
    public void testNonExistentItem()
    {
        webDriver.get(baseURL);

        acceptCookies();

        WebElement inputField = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div/div[1]/form/input"));
        inputField.sendKeys("h34haa34h0" + Keys.ENTER);

        WebElement notFoundH2 = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div[2]/section[1]/div[1]/h2"));
        String notFoundText = notFoundH2.getText();

        assertEquals("NAŽALOST NISMO USPJELI PRONAĆI: H34HAA34H0", notFoundText);
    }


    @Test
    public void testSqlInjectionProof() throws InterruptedException
    {
        webDriver.get(baseURL);

        acceptCookies();

        WebElement inputField = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div/div[1]/form/input"));
        inputField.sendKeys("SELECT * FROM USERS" + Keys.ENTER);

        shortWait();

        WebElement accessDeniedH2 = webDriver.findElement(By.xpath("/html/body/h1"));
        String accessDeniedText = accessDeniedH2.getText();

        assertEquals("Access Denied", accessDeniedText);
    }

}
