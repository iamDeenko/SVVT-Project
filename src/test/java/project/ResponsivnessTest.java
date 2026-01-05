package project;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ResponsivnessTest extends BaseTest {
    @Test
    public void burgerMenuDisplayedOnMobile() throws InterruptedException {
        webDriver.get(appendBaseURL(""));
        acceptCookies();
        shortWait();

        // Resize window to mobile resolution
        webDriver.manage().window().setSize(new Dimension(400, 800));
        shortWait();

        // Locate hamburger menu
        WebElement burgerMenu = webDriver.findElement(
                By.cssSelector("a.megamenu-toggle[aria-label='Meni']")
        );

        // Assert burger menu is visible
        Assertions.assertTrue(
                burgerMenu.isDisplayed(),
                "Burger menu is not visible on mobile resolution"
        );
    }

    @Test
    public void noHorizontalOverflow() throws InterruptedException {
        webDriver.get(baseURL);
        acceptCookies();
        webDriver.manage().window().setSize(new Dimension(400, 800));
        shortWait();

        Long scrollWidth = (Long) ((JavascriptExecutor) webDriver).executeScript("return document.body.scrollWidth;");
        Long innerWidth  = (Long) ((JavascriptExecutor) webDriver).executeScript("return window.innerWidth;");

        Assertions.assertTrue(scrollWidth <= innerWidth, "Page has horizontal overflow on mobile");
    }
}
