package project;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FooterTest extends BaseTest {
    @Test
    public void spavacaSobaLinkTest() throws InterruptedException {
        webDriver.get(baseURL);
        acceptCookies();
        WebElement link = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[6]/div/footer/div[1]/div[1]/div[1]/nav/ul/li[1]/a"));
        scrollAndClick(link);
        shortWait();
        Assertions.assertNotEquals(webDriver.getCurrentUrl(),baseURL);

    }

    @Test
    public void customerServiceLinkTest() throws InterruptedException {
        webDriver.get(baseURL);
        acceptCookies();
        WebElement link = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[6]/div/footer/div[1]/div[2]/div[1]/nav/ul/li[1]/a"));
        scrollAndClick(link);
        shortWait();
        Assertions.assertNotEquals(webDriver.getCurrentUrl(),baseURL);
    }

    @Test
    public void aboutLinkTest() throws InterruptedException {
        webDriver.get(baseURL);
        acceptCookies();
        WebElement link = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[6]/div/footer/div[1]/div[3]/div[1]/nav/ul/li[1]/a"));
        scrollAndClick(link);
        shortWait();
        Assertions.assertNotEquals(webDriver.getCurrentUrl(),baseURL);
    }
}
