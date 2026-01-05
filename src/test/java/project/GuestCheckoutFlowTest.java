package project;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuestCheckoutFlowTest extends BaseTest {
    @Test
    public void testCheckoutFlow() throws InterruptedException {
        webDriver.get(appendBaseURL("spavaca-soba/madraci/madraci-s-oprugama/madrac-s-oprugama-80x200-banelva-srednje-tvrd"));

        acceptCookies();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(12));
        By addToCartBtn = By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/article/section/main/div[1]/div[2]/div/div/div/div/div[5]/div/button");
        WebElement cartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartBtn));
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});", cartBtn
        );
        cartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        cartBtn.click();
        By pregledKorpeBtn = By.xpath("/html/body/div[9]/div[4]/div[2]/div/div/a");
        WebElement pregledBtn = wait.until(ExpectedConditions.presenceOfElementLocated(pregledKorpeBtn));
        pregledBtn.click();
        By cartItems = By.cssSelector("li.basket-item");
        int itemCount = webDriver.findElements(cartItems).size();

        Assertions.assertTrue(
                itemCount > 0,
                "Cart is empty – no basket items found"
        );

        By nastaviteBtn = By.xpath("//*[@id=\"checkout\"]/div/div/div[3]/div[1]/div[2]/div/a[2]");
        WebElement nastaviteBtn2 = wait.until(ExpectedConditions.elementToBeClickable(nastaviteBtn));
        nastaviteBtn2.click();

        Assertions.assertTrue(webDriver.getCurrentUrl().equals("https://jysk.ba/basket/address"));

        // Ensure form is visible
        By form = By.cssSelector("form[data-test='checkoutAddressForm']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(form));

        // Locators (from your HTML)
        By firstName = By.cssSelector("input[data-test='firstNameField']#edit-firstname");
        By lastName  = By.cssSelector("input[data-test='lastNameField']#edit-lastname");
        By street    = By.cssSelector("input[data-test='streetField']#edit-street");
        By number    = By.cssSelector("input[data-test='numberField']#edit-number");
        By zip       = By.cssSelector("input[data-test='zipCodeField']#edit-zipcode");
        By city      = By.cssSelector("input[data-test='cityField']#edit-city");
        By email     = By.cssSelector("input[data-test='emailField']#edit-email");
        By mobile    = By.cssSelector("input[data-test='mobileField']#edit-mobile");
        By terms     = By.cssSelector("input[data-test='termsAcceptedCheckbox']#edit-termsaccepted");
        By continueBtn = By.cssSelector("input[data-test='toCarriers']#edit-submit");

        // Fill fields (example data)
        type(wait, firstName, "Aid");
        type(wait, lastName, "Ajkunic");
        type(wait, street, "Zmaja od Bosne");
        type(wait, number, "12");
        type(wait, zip, "71000");
        type(wait, city, "Sarajevo");
        type(wait, email, "aid.test@example.com");
        type(wait, mobile, "061123456");

        WebElement formEl = wait.until(ExpectedConditions.visibilityOfElementLocated(form));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 600);");

        // Accept terms (only if not already checked)
        WebElement termsCb = wait.until(ExpectedConditions.elementToBeClickable(terms));
        if (!termsCb.isSelected()) {
            termsCb.click();
        }

        // Click continue
        WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        shortWait();
        cont.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/basket/address")));

        Assertions.assertFalse(
                webDriver.getCurrentUrl().contains("/basket/address"),
                "Still on address step after clicking Nastavite"
        );

        WebElement radio = webDriver.findElement(By.xpath("//*[@id=\"edit-shop-selector\"]/div[1]/div[4]/div[1]/div[3]/label/input"));

        scrollAndClick(radio);

        Assertions.assertTrue(radio.isSelected());

        WebElement toPaymentBtn = webDriver.findElement(By.xpath("//*[@id=\"edit-submit\"]"));
        scrollAndClick(toPaymentBtn);

        Assertions.assertTrue(
                webDriver.getCurrentUrl().contains("/basket/payment"),
                "Still on address step after clicking Nastavite"
        );



    }

    // Helper method to fill inputs reliably
    private void type(WebDriverWait wait, By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.DELETE);
        el.sendKeys(text);
    }


}
