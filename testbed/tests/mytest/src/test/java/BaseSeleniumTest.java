import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;
import org.openqa.selenium.support.ui.*;

public class BaseSeleniumTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        try {
            driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, 120);
    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
