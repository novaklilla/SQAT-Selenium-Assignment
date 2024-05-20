package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class GoodreadsHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public GoodreadsHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public boolean isTitleContaining(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }
}
