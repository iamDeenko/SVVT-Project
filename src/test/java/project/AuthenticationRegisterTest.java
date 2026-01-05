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

        // Timestamp is used to create a unique address so that we can always register and test this flow.
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


    @Test
    public void testRegistrationInvalidEmail() throws InterruptedException
    {
        webDriver.get(appendBaseURL("customer/create"));

        acceptCookies();

        //Here we will make an invalid email address
        String invalidEmail = "test.@googlecom";
        String password = "Test1234!";
        String firstName = "Test";
        String lastName = "Testinovich";

        webDriver.findElement(By.id("email")).sendKeys(invalidEmail);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("passwordConfirm")).sendKeys(password);
        webDriver.findElement(By.id("firstName")).sendKeys(firstName);
        webDriver.findElement(By.id("lastName")).sendKeys(lastName);

        // Get the button, then click with JS using the scrollAndClick function
        WebElement tosButton = webDriver.findElement(By.xpath(
                "/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/form/div/div[2]/div[7]/div/div/div/label/input"
        ));

        scrollAndClick(tosButton);

        // Submit button - same deal again :D
        WebElement submitButton = webDriver.findElement(By.xpath(
                "/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/form/div/div[3]/button"
        ));
        scrollAndClick(submitButton);

        shortWait();

        // If the email is invalid ( which it is ) there should be a "Please enter a valid e-mail" span below the email input field.
        WebElement invalidEmailSpan = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/form/div/div[2]/div[1]/div[2]/span"));
        scrollTo(invalidEmailSpan);
        String invalidEmailText = invalidEmailSpan.getText();

`        assertEquals("Please enter a valid e-mail", invalidEmailText);
    }


    @Test
    public void testRegistrationInvalidPassword() throws InterruptedException
    {
        webDriver.get(appendBaseURL("customer/create"));

        acceptCookies();

        String timestamp = String.valueOf(System.currentTimeMillis());
        //Here we will make an invalid password
        String invalidEmail = "test@test." + timestamp + ".com";
        String password = "bb";`
        String firstName = "Test";
        String lastName = "Testinovich";

        webDriver.findElement(By.id("email")).sendKeys(invalidEmail);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("passwordConfirm")).sendKeys(password);
        webDriver.findElement(By.id("firstName")).sendKeys(firstName);
        webDriver.findElement(By.id("lastName")).sendKeys(lastName);

        // Get the button, then click with JS using the scrollAndClick function
        WebElement tosButton = webDriver.findElement(By.xpath(
                "/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/form/div/div[2]/div[7]/div/div/div/label/input"
        ));

        scrollAndClick(tosButton);

        // Submit button - same deal again :D
        WebElement submitButton = webDriver.findElement(By.xpath(
                "/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/form/div/div[3]/button"
        ));
        scrollAndClick(submitButton);

        shortWait();

        // If the password is invalid ( which it is ) there should be a "Your password is invalid..." span below the password input field.
        WebElement invalidEmailSpan = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/div/div/section/form/div/div[2]/div[3]/div[2]/span"));
        scrollTo(invalidEmailSpan);

        String invalidEmailText = invalidEmailSpan.getText();

        // We have to scroll on the element

        assertEquals("Your password must contain minimum 8 characters and at least one uppercase letter, one lowercase letter and one number", invalidEmailText);
    }
}
