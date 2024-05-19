import static org.junit.Assert.assertTrue;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;
import org.openqa.selenium.support.ui.*;

public class GoodReadsSeleniumTest {

    private WebDriver driver;
	private WebDriverWait wait;

    @Before
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		
		try {
			driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		this.driver.manage().window().maximize();
		
		this.wait = new WebDriverWait(driver, 100);
	}

    @Test
    public void testLoadGoodreadsHomepage() {
        driver.get("https://www.goodreads.com/");
        String pageTitle = driver.getTitle();
        assertTrue("Page title does contain Goodreads", pageTitle.contains("Goodreads"));
    }

    @After
    public void close() 
	{
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
