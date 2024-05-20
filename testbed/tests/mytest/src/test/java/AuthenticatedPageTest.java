import PageObjects.AuthenticatedPage;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticatedPageTest extends BaseSeleniumTest {
    private AuthenticatedPage authenticatedPage;
    private final String email = "seleniumtest519@gmail.com";
    private final String password = "SH65*X84Be";
    private final String webPageReviewBook = "https://www.goodreads.com/book/show/51467738-lake-balaton";

    @Before
    public void setUp() {
        authenticatedPage = new AuthenticatedPage(driver, wait);
    }

    @Test
    public void testBookReviewing() {
        authenticatedPage.signIn(email, password);
        authenticatedPage.goToBookReviewPage(webPageReviewBook);
        authenticatedPage.chooseShelfAndMarkAsRead();
        authenticatedPage.editActivity();
        authenticatedPage.rateBook("it was amazing");
        authenticatedPage.writeReview("Great book, loved it!");
        authenticatedPage.setStartDate("4", "18");
        authenticatedPage.setEndDate();
        authenticatedPage.addToBlog();
        authenticatedPage.postReview();
        assertTrue("Review is successfully posted", authenticatedPage.isReviewPosted("Great book, loved it!"));
    }

    @Test
    public void testMyBooksHover() {
        authenticatedPage.signIn(email, password);
        authenticatedPage.goToMyBooksPage();

        WebElement didNotLikeItStar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='did not like it']")));

        WebElement itWasAmazingStar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='it was amazing']")));

        String classBeforeHover = itWasAmazingStar.getAttribute("class");
        System.out.println("Class before hover: " + classBeforeHover);

        Actions actions = new Actions(driver);
        actions.moveToElement(didNotLikeItStar).perform();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String classAfterHover = itWasAmazingStar.getAttribute("class");
        System.out.println("Class after hover: " + classAfterHover);

        assertNotEquals("Star class should change after hover", classBeforeHover, classAfterHover);
        assertEquals("Star class should be 'star off' after hover", "star off", classAfterHover);
    }
}
