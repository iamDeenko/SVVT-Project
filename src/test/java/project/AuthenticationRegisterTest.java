package project;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationRegisterTest extends BaseTest
{

    @Test
    public void testRegistration() throws InterruptedException {
        webDriver.get(appendBaseURL("customer/create"));

        String timestamp = String.valueOf(System.currentTimeMillis());
        acceptCookies();

        String email = "svvt.test." + timestamp + "@stu.ibu.edu.ba";
        String password = "Test1234!";
        String firstName = "Test";
        String lastName = "Testinovich";

        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("passwordConfirm")).sendKeys(password);
        webDriver.findElement(By.id("firstName")).sendKeys(firstName);
        webDriver.findElement(By.id("lastName")).sendKeys(lastName);


        // Get the button, then click with JS using the scrollAndClick function
        WebElement tosButton = webDriver.findElement(By.xpath(
                "/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/form/div/div[2]/div[7]/div/div/div/label/input"
        ));

        scrollAndClick(tosButton);

        // Submit button - same deal
        WebElement submitButton = webDriver.findElement(By.xpath(
                "/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/form/div/div[3]/button"
        ));

        scrollAndClick(submitButton);

        // Wait for modal
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement registrationValue = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.modal.show .modal-title")
                )
        );

        String registrationText = registrationValue.getText();

        assertEquals("Vaš je korisnički račun stvoren.", registrationText);
    }
}
