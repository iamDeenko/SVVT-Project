package project;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class StoreLocatorTest extends BaseTest {
    @Test
    public void locateStores() throws InterruptedException {
        webDriver.get(appendBaseURL("prodavnice-i-radno-vrijeme"));
        acceptCookies();
        shortWait();
        WebElement searchInput = webDriver.findElement(
                By.xpath("/html/body/div[1]/div/div[2]/div[3]/section/div/section/div/div/div/div[1]/div/div/div[1]/form/input")
        );


        // 2) Type city name
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys("Kakanj");
        searchInput.sendKeys(Keys.ENTER);

        // Allow search results to update
        longWait();

        boolean found = webDriver.findElements(By.xpath("//*[contains(normalize-space(.), 'Kakanj')]")).size() > 0;

        Assertions.assertTrue(found, "After searching 'Kakanj', no store entry/text containing 'Kakanj' was found");
    }

    @Test
    public void wrongInput() throws InterruptedException {
        webDriver.get(appendBaseURL("prodavnice-i-radno-vrijeme"));
        acceptCookies();
        shortWait();

        WebElement searchInput = webDriver.findElement(
                By.xpath("/html/body/div[1]/div/div[2]/div[3]/section/div/section/div/div/div/div[1]/div/div/div[1]/form/input")
        );

        // Type invalid city
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys("zzzzz");
        searchInput.sendKeys(Keys.ENTER);

        // Allow search results to update
        longWait();

        // Assert NO store entry contains "zzzzz"
        boolean found = webDriver.findElements(
                By.xpath("//*[contains(normalize-space(.), 'zzzzz')]")
        ).size() > 0;

        Assertions.assertFalse(
                found,
                "Unexpected store entries were shown for invalid search input 'zzzzz'"
        );

    }

    @Test
    public void partialSearch1() throws InterruptedException {
        webDriver.get(appendBaseURL("prodavnice-i-radno-vrijeme"));
        acceptCookies();
        shortWait();

        WebElement searchInput = webDriver.findElement(
                By.xpath("/html/body/div[1]/div/div[2]/div[3]/section/div/section/div/div/div/div[1]/div/div/div[1]/form/input")
        );

// Type PARTIAL city name
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys("Kak");
        searchInput.sendKeys(Keys.ENTER);

// Allow search results to update
        longWait();

// Assert at least one store entry contains "Kakanj"
        boolean found = webDriver.findElements(
                By.xpath("//*[contains(normalize-space(.), 'Kakanj')]")
        ).size() > 0;

        Assertions.assertTrue(
                found,
                "Partial search 'Kak' did not return expected store 'Kakanj'"
        );

    }

    @Test
    public void partialSearch2() throws InterruptedException {
        webDriver.get(appendBaseURL("prodavnice-i-radno-vrijeme"));
        acceptCookies();
        shortWait();

        WebElement searchInput = webDriver.findElement(
                By.xpath("/html/body/div[1]/div/div[2]/div[3]/section/div/section/div/div/div/div[1]/div/div/div[1]/form/input")
        );

// Type PARTIAL city name
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys("Sar");
        searchInput.sendKeys(Keys.ENTER);

// Allow search results to update
        longWait();

// Assert at least one store entry contains "Sarajevo"
        boolean found = webDriver.findElements(
                By.xpath("//*[contains(normalize-space(.), 'Sarajevo')]")
        ).size() > 0;

        Assertions.assertTrue(
                found,
                "Partial search 'Kak' did not return expected store 'Sarajevo'"
        );

        // Click the map marker (button)
        WebElement markerBtn = webDriver.findElement(By.cssSelector(
                "div[role='button'][aria-label='Novo Sarajevo, Sarajevo']"
        ));

        try {
            markerBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", markerBtn);
        }

        longWait();

// Assert info window appears with Sarajevo
        WebElement storeName = webDriver.findElement(By.cssSelector(".stores-locator-info-window .store-name"));
        String storeNameText = storeName.getText();

        Assertions.assertTrue(
                storeNameText.toLowerCase().contains("sarajevo"),
                "Info window did not open for Sarajevo marker. Actual: " + storeNameText
        );

    }


}
