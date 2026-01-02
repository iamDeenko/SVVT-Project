package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest
{

    protected WebDriver webDriver;
    protected String baseURL = "http://jysk.ba/";
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
    public void acceptCookies() throws InterruptedException
    {
        this.shortWait();
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        this.shortWait();
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