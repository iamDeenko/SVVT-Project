package project;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest extends BaseTest
{

    @Test
    public void addItemToCart() throws InterruptedException
    {
        webDriver.get(appendBaseURL("search?query=madrac&type=product"));

        acceptCookies();


        // Click on the first item's picture because it's in an anchor tag that leads to the next product.
        // The XPATH should always be the same, so unless they remove all mattresses for some reason the test should always work.
        WebElement firstItemPicture = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div[2]/section/div[2]/div[1]/div[3]/div[1]/div/div/div[1]/a"));
        scrollAndClick(firstItemPicture);


        shortWait();


        // Now we click on the "Dodaj u korpu" button to add the item to the cart.
        WebElement addToCartButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/article/section/main/div[1]/div[2]/div/div/div/div/div[5]/div/button/span"));
        scrollAndClick(addToCartButton);

        longWait();

        // After waiting for a little bit for the modal to pop up, we click on the "Pregled Korpe" button.
        webDriver.findElement(By.xpath("/html/body/div[9]/div[4]/div[2]/div/div/a")).click();

        longWait();

        // Now we count the amount of <li> tags inside the <ul> that holds the cart items.
        WebElement ulElement = webDriver.findElement(By.cssSelector(".basket-items"));

        // We make a List of WebElement items that we find by the li tagname. When we print the size should be 1, since we only have one item.
        List<WebElement> liItems = ulElement.findElements(By.tagName("li"));
        int count = liItems.size();

        assertEquals(1, count);
    }


    @Test
    public void testQuantityInCart() throws InterruptedException
    {
        // Here we are adding a mattress to the cart.
        webDriver.get(appendBaseURL("spavaca-soba/madraci/madraci-s-oprugama/madrac-s-oprugama-80x200-banelva-srednje-tvrd"));

        acceptCookies();

        // Go to the quantity div, scroll to it, select 2.
        WebElement quantityButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/article/section/main/div[1]/div[2]/div/div/div/div/div[5]/div/div/button[2]"));
        scrollAndClick(quantityButton);

        // Add the items to cart.
        WebElement addToCart = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/article/section/main/div[1]/div[2]/div/div/div/div/div[5]/div/button"));
        scrollAndClick(addToCart);

        // Wait a while for the div to show up.
        shortWait();


        // Click the "Pregled kupovnje button"
        webDriver.findElement(By.xpath("/html/body/div[9]/div[4]/div[2]/div/div/a")).click();


        // Get the quantity from the div and store it in an int to compare
        WebElement quantityDiv = webDriver.findElement(By.xpath("/html/body/div[1]/div/section/div/div/div[3]/div[1]/div[1]/div/div/ul/li/div[3]/input"));
        String quantity = quantityDiv.getAttribute("value");

        assertEquals("2", quantity);
    }


    @Test
    public void testCartItemRemoval() throws InterruptedException
    {
        // Open the link to a black lounge ubberup chair
        webDriver.get(appendBaseURL("basta/lounge-namjestaj/lounge-stolice/lounge-stolica-ubberup-crna"));


        acceptCookies();


        // Scroll & click on the "Add to cart button"
        WebElement addToCart = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/article/section/main/div[1]/div[2]/div/div/div/div/div[4]/div/button"));
        scrollAndClick(addToCart);


        shortWait();


        // Continue checkout.
        webDriver.findElement(By.xpath("/html/body/div[9]/div[3]/div[2]/div/div/a")).click();


        shortWait();


        // Click on the empty cart button.
        webDriver.findElement(By.xpath("/html/body/div[1]/div/section/div/div/div[3]/div[1]/div[1]/div/div/ul/li/button")).click();


        shortWait();


        // If the operation is successful, this div SHOULD exist and should display "Korpa je prazna"
        WebElement emptyCartText = webDriver.findElement(By.xpath("/html/body/div[1]/div/section/div/div/div[3]/div[1]/div[1]/div[1]/div"));
        String currentText = emptyCartText.getText();

        assertEquals("Korpa je prazna", currentText);
    }
}
