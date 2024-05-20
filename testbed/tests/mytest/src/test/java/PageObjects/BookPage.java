package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class BookPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BookPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToBookPage(String url) {
        driver.get(url);
    }

    public boolean isBookDescriptionDisplayed(String description) {
        WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + description + "')]")));
        return descriptionElement.isDisplayed();
    }

    public boolean isTitleContaining(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
