package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;

public class AuthenticatedPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AuthenticatedPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void signIn(String email, String password) {
        driver.get("https://www.goodreads.com/user/sign_in");

        WebElement signInWithEmailButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign in with email')]")));
        signInWithEmailButton.click();

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.sendKeys(password);

        WebElement rememberMeCheckbox = driver.findElement(By.name("rememberMe"));
        rememberMeCheckbox.click();

        WebElement signInButton = driver.findElement(By.id("signInSubmit"));
        signInButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Currently Reading']")));
    }

    public void goToBookReviewPage(String bookUrl) {
        driver.get(bookUrl);
    }

    public void chooseShelfAndMarkAsRead() {
        WebElement chooseShelfButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Tap to choose a shelf for this book']/span")));
        chooseShelfButton.click();

        WebElement readButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Read']/span[contains(text(),'Read')]")));
        readButton.click();

        WebElement overlayActionsDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Overlay__actions")));

        WebElement doneButton = overlayActionsDiv.findElement(By.xpath(".//button[.//span[text()='Done']]"));
        doneButton.click();
    }

    public void editActivity() {
		WebDriverWait overlayWait = new WebDriverWait(driver, 10);
		overlayWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("Overlay--floating")));

		WebElement editActivityLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.//span[contains(@class, 'Button__labelItem') and text()='Edit my activity']]")));
		editActivityLink.click();
	}

    public void rateBook(String ratingTitle) {
        WebElement ratingElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'star')][@title='" + ratingTitle + "']")));
        ratingElement.click();
    }

    public void writeReview(String reviewText) {
        WebElement reviewTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review_review_usertext")));
        reviewTextArea.sendKeys(reviewText);
    }

    public void setStartDate(String month, String day) {
        WebElement setStartDateLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'startedAtSetTodayLink')]")));
        setStartDateLink.click();
        selectDate(month, day, "startMonth", "startDay");
    }

    public void setEndDate() {
        WebElement setEndDateLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'endedAtSetTodayLink')]")));
        setEndDateLink.click();
    }

    public void addToBlog() {
        WebElement postToBlogCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_to_blog")));
        postToBlogCheckbox.click();
    }

    public void postReview() {
        WebElement postButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Post']")));
        postButton.click();
    }

    public boolean isReviewPosted(String reviewText) {
        WebElement reviewsDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[itemprop='reviews']")));
        WebElement reviewElement = reviewsDiv.findElement(By.xpath(".//*[contains(text(), '" + reviewText + "')]"));
        return reviewElement.isDisplayed();
    }

    private void selectDate(String month, String day, String monthSelector, String daySelector) {
        WebElement monthSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[contains(@class, '" + monthSelector + "')]")));
        monthSelect.sendKeys(month);

        WebElement daySelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[contains(@class, '" + daySelector + "')]")));
        daySelect.sendKeys(day);
    }

    public void goToMyBooksPage() {
        WebElement myBooksMenuItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/review/list/178373836?ref=nav_mybooks']")));
        myBooksMenuItem.click();
    }
}
