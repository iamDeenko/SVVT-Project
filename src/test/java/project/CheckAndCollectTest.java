package project;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckAndCollectTest extends BaseTest {
    @Test
    public void testCheckAndCollect() throws InterruptedException {
        webDriver.get(appendBaseURL("spavaca-soba/madraci/madraci-s-oprugama/madrac-s-oprugama-80x200-banelva-srednje-tvrd"));
        acceptCookies();
        shortWait();
        WebElement checkAndCollectBtn = webDriver.findElement(By.xpath("//*[@id=\"2\"]"));
        scrollAndClick(checkAndCollectBtn);
        shortWait();
        WebElement content = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/article/section/main/div[1]/div[2]/div/div/div/div/div[4]/form/div[2]/div/div/span[2]/div[1]"));
        Assertions.assertTrue(content.isDisplayed());
        Assertions.assertTrue(content.getText().contains("Na zalihama") || content.getText().contains("Nema na zalihama"));
    }
}
