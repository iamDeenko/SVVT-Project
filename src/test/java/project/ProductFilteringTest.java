package project;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductFilteringTest extends BaseTest {
    @Test
    public void testPriceFilter() throws InterruptedException {
        webDriver.get(appendBaseURL("spavaca-soba/madraci/madraci-s-oprugama"));
        acceptCookies();
        shortWait();
        WebElement priceBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/section/div/main/div/div/div/div[3]/div[1]/div/div/div[1]/div/button[1]"));
        priceBtn.click();
        WebElement maxPriceInput = webDriver.findElement(By.id("thumb-Max"));

        maxPriceInput.click();
        maxPriceInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        maxPriceInput.sendKeys(Keys.DELETE);
        maxPriceInput.sendKeys("300");
        maxPriceInput.sendKeys(Keys.ENTER);

        shortWait();

        WebElement minPriceInput = webDriver.findElement(By.id("thumb-Min"));
        minPriceInput.click();
        minPriceInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        minPriceInput.sendKeys(Keys.DELETE);
        minPriceInput.sendKeys("200");
        minPriceInput.sendKeys(Keys.ENTER);

        shortWait();

        WebElement showBtn = webDriver.findElement(By.xpath("//*[@id=\"canvas_root\"]/div[2]/div[2]/div/div[2]/button[2]"));
        showBtn.click();

        List<WebElement> prices = webDriver.findElements(
                By.className("product-teaser-price-wrapper")
        );

        for (WebElement priceEl : prices) {
            String priceText = priceEl.getText().substring(0,3);   // e.g. "135,- KM"

            // Remove everything except digits
            int price = Integer.parseInt(
                    priceText
            );

            Assertions.assertTrue(
                    price <= 300 && price >= 200,
                    "Product price exceeds filter limit: " + priceText
            );
        }
    }

    @Test
    public void testWrongInputPriceFilter() throws InterruptedException {
        webDriver.get(appendBaseURL("spavaca-soba/madraci/madraci-s-oprugama"));
        acceptCookies();
        shortWait();
        WebElement priceBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/section/div/main/div/div/div/div[3]/div[1]/div/div/div[1]/div/button[1]"));
        priceBtn.click();
        WebElement maxPriceInput = webDriver.findElement(By.id("thumb-Max"));

        maxPriceInput.click();
        maxPriceInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        maxPriceInput.sendKeys(Keys.DELETE);
        maxPriceInput.sendKeys("300");
        maxPriceInput.sendKeys(Keys.ENTER);

        shortWait();

        WebElement minPriceInput = webDriver.findElement(By.id("thumb-Min"));
        minPriceInput.click();
        minPriceInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        minPriceInput.sendKeys(Keys.DELETE);
        minPriceInput.sendKeys("400");
        minPriceInput.sendKeys(Keys.ENTER);

        shortWait();

        WebElement showBtn = webDriver.findElement(By.xpath("//*[@id=\"canvas_root\"]/div[2]/div[2]/div/div[2]/button[2]"));
        showBtn.click();

        shortWait();

        priceBtn.click();

        minPriceInput = webDriver.findElement(By.id("thumb-Min"));
        maxPriceInput = webDriver.findElement(By.id("thumb-Max"));
        Assertions.assertTrue(minPriceInput.getAttribute("value").equals(maxPriceInput.getAttribute("value")));


    }

    @Test
    public void testResetFilter() throws InterruptedException {
        webDriver.get(appendBaseURL("spavaca-soba/madraci/madraci-s-oprugama"));
        acceptCookies();
        shortWait();
        WebElement priceBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/section/div/main/div/div/div/div[3]/div[1]/div/div/div[1]/div/button[1]"));
        priceBtn.click();
        WebElement maxPriceInput = webDriver.findElement(By.id("thumb-Max"));

        maxPriceInput.click();
        maxPriceInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        maxPriceInput.sendKeys(Keys.DELETE);
        maxPriceInput.sendKeys("300");
        maxPriceInput.sendKeys(Keys.ENTER);

        shortWait();

        WebElement minPriceInput = webDriver.findElement(By.id("thumb-Min"));
        minPriceInput.click();
        minPriceInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        minPriceInput.sendKeys(Keys.DELETE);
        minPriceInput.sendKeys("200");
        minPriceInput.sendKeys(Keys.ENTER);

        shortWait();

        WebElement showBtn = webDriver.findElement(By.xpath("//*[@id=\"canvas_root\"]/div[2]/div[2]/div/div[2]/button[2]"));
        showBtn.click();

        shortWait();

        WebElement resetPrice = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/section/div/main/div/div/div/div[3]/div[2]/div/button"));
        scrollAndClick(resetPrice);
        priceBtn.click();
        minPriceInput = webDriver.findElement(By.id("thumb-Min"));
        maxPriceInput = webDriver.findElement(By.id("thumb-Max"));
        Assertions.assertTrue(!minPriceInput.getAttribute("value").equals("200") && !maxPriceInput.getAttribute("value").equals("300"));


    }
}
