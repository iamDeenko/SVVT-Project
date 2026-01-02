package project;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddItemToCartTest extends BaseTest
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

}
