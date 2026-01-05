package project;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductSortingTest extends BaseTest {
    @Test
    public void testProductSortingAsc() throws InterruptedException {
        webDriver.get(appendBaseURL("spavaca-soba/madraci/madraci-s-oprugama"));
        acceptCookies();
        shortWait();

        WebElement sortBtn = webDriver.findElement(By.xpath("//*[@id=\"product-list-page\"]/div/div/div[3]/div[1]/div/div/div[1]/div/button[4]"));
        sortBtn.click();
        shortWait();
        WebElement prvoNajjBtn = webDriver.findElement(By.xpath("//*[@id=\"sort__price-asc\"]"));
        prvoNajjBtn.click();
        shortWait();
        WebElement showBtn = webDriver.findElement(By.xpath("//*[@id=\"canvas_root\"]/div[2]/div[2]/div/div[2]/button[2]"));
        showBtn.click();
        shortWait();
        List<WebElement> priceEl = webDriver.findElements(
                By.className("product-teaser-price-wrapper")
        );
        List<Integer> priceList = new ArrayList<Integer>();
        for (WebElement priceel : priceEl) {
            String priceText = priceel.getText().substring(0,3);   // e.g. "135,- KM"

            // Remove everything except digits
            int price = Integer.parseInt(
                    priceText
            );
            priceList.add(price);

        }

        for (int i = 1; i < priceList.size(); i++) {
            Assertions.assertTrue(
                    priceList.get(i) >= priceList.get(i - 1),
                    "Sorting failed: " + priceList.get(i - 1) + " > " + priceList.get(i)
            );
        }
    }

    @Test
    public void testProductSortingDesc() throws InterruptedException {
        webDriver.get(appendBaseURL("spavaca-soba/madraci/madraci-s-oprugama"));
        acceptCookies();
        shortWait();

        WebElement sortBtn = webDriver.findElement(By.xpath("//*[@id=\"product-list-page\"]/div/div/div[3]/div[1]/div/div/div[1]/div/button[4]"));
        sortBtn.click();
        shortWait();
        WebElement prvoNajsBtn = webDriver.findElement(By.xpath("//*[@id=\"sort__price-desc\"]"));
        prvoNajsBtn.click();
        shortWait();
        WebElement showBtn = webDriver.findElement(By.xpath("//*[@id=\"canvas_root\"]/div[2]/div[2]/div/div[2]/button[2]"));
        showBtn.click();
        shortWait();
        List<WebElement> priceEl = webDriver.findElements(
                By.className("product-teaser-price-wrapper")
        );
        List<Integer> priceList = new ArrayList<Integer>();
        for (WebElement priceel : priceEl) {
            String priceText = priceel.getText().substring(0,3);   // e.g. "135,- KM"

            // Remove everything except digits
            int price = Integer.parseInt(
                    priceText
            );
            priceList.add(price);

        }

        for (int i = 1; i < priceList.size(); i++) {
            Assertions.assertTrue(
                    priceList.get(i) <= priceList.get(i - 1),
                    "Sorting failed: " + priceList.get(i - 1) + " < " + priceList.get(i)
            );
        }
    }
}
