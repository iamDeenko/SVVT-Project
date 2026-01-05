package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class BaseTest
{

    protected WebDriver webDriver;
    protected String baseURL = "https://jysk.ba/";
    protected JavascriptExecutor javascriptExecutor;


    @BeforeEach
    public void setUp() throws InterruptedException
    {
        Thread.sleep(2500);
        webDriver = new ChromeDriver();

        javascriptExecutor = (JavascriptExecutor) webDriver;

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() throws InterruptedException
    {
        Thread.sleep(2500);
        webDriver.close();
    }


    public String appendBaseURL(String newURL)
    {
        return this.baseURL + newURL;
    }

    public String getBaseURL()
    {
        return baseURL;
    }


    // Helper method I made to click on the annoying cookies popup that shows on browser opening.
    public void acceptCookies() {
        try {
            List<WebElement> acceptButton = webDriver.findElements(By.id("onetrust-accept-btn-handler"));

            if (!acceptButton.isEmpty() && acceptButton.get(0).isDisplayed()) {
                acceptButton.get(0).click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already accepted. Skipping...");
        }
    }

    // This website also has a popup that shows from time to time, blocking the forEach loop in the NavLinkTest class.
    public void closeNewsletterIfPresent() {
        try {
            // This is a common selector for the 'X' button on JYSK popups
            // Using a list so we don't throw an error if it's not there
            var closeButtons = webDriver.findElements(By.cssSelector("button.newsletter-popup-close-btn, .modal-header .close"));
            if (!closeButtons.isEmpty() && closeButtons.get(0).isDisplayed()) {
                closeButtons.get(0).click();
                shortWait(); // Wait for the backdrop to fade out
            }
        } catch (Exception e) {
            // If it fails to close, we just try to move on
        }
    }


    // Another helper method I made that scrolls to things and clicks them.
    public void scrollAndClick(WebElement webElement)
    {
        javascriptExecutor.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                webElement
        );

        // JS click
        javascriptExecutor.executeScript("arguments[0].click();", webElement);
    }


    // Helper method I made that only scrolls to things
    public void scrollTo(WebElement webElement)
    {
        javascriptExecutor.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                webElement
        );
    }



    // Waits 1000 milliseconds.
    public void shortWait() throws InterruptedException
    {
        Thread.sleep(1000);
    }

    //Waits 3000 milliseconds.
    public void longWait() throws InterruptedException
    {
        Thread.sleep(3000);
    }
}