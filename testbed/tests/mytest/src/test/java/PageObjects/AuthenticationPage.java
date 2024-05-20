package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class AuthenticationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AuthenticationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isSignInTextDisplayed() {
        WebElement signInText = driver.findElement(By.xpath("//a[contains(text(),'Sign In')]"));
        return signInText.isDisplayed();
    }
	
	public boolean isCurrentlyReadingHeaderDisplayed() {
        WebElement currentlyReadingHeader = driver.findElement(By.xpath("//h3[text()='Currently Reading']"));
        return currentlyReadingHeader.isDisplayed();
    }
	
	public void signIn(String email, String password) {
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

    public void signOut() {
        WebElement avatarMenuButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'dropdown__trigger--profileMenu')]")));
        avatarMenuButton.click();

        WebElement dropdownMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'dropdown__menu--profileMenu')]")));

        WebElement signOutLink = dropdownMenu.findElement(By.xpath("//a[contains(@href,'/user/sign_out')]"));
        signOutLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Sign In')]")));
    }

	public String getErrorMessage() {
		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("auth-error-message-box")));
		return errorMessage.getText();
	}
}
