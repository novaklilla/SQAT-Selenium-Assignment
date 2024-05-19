import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.URL;
import java.net.MalformedURLException;

public class GoodReadsSeleniumTest {

    private WebDriver driver;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        try {
            driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
    }

    @Test
    public void testLoadGoodreadsHomepage() {
        driver.get("https://www.goodreads.com/");
        String pageTitle = driver.getTitle();
        assertTrue("Page title does not contain Goodreads", pageTitle.contains("Goodreads"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
