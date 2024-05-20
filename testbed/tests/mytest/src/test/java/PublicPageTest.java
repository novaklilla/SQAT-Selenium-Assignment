import PageObjects.GoodreadsHomePage;
import PageObjects.BookPage;

import org.junit.*;
import static org.junit.Assert.*;

public class PublicPageTest extends BaseSeleniumTest {
    private GoodreadsHomePage goodreadsHomePage;
    private BookPage bookPage;
    private String webPageStaticBook = "https://www.goodreads.com/book/show/61439040-1984";

    @Before
    public void setUp() {
        goodreadsHomePage = new GoodreadsHomePage(driver, wait);
        bookPage = new BookPage(driver, wait);
    }

    @Test
    public void testLoadGoodreadsHomepage() {
        goodreadsHomePage.navigateToHomePage("https://www.goodreads.com/");
        assertTrue("Page title does contain Goodreads", goodreadsHomePage.isTitleContaining("Goodreads"));
    }

    @Test
    public void testBookPageDescription() {
        bookPage.navigateToBookPage(webPageStaticBook);

        String bookDescription = "A masterpiece of rebellion and imprisonment";
        assertTrue("Book description is present", bookPage.isBookDescriptionDisplayed(bookDescription));
    }

    @Test
    public void testPageTitle() {
        bookPage.navigateToBookPage(webPageStaticBook);

        assertTrue("Page title contains '1984'", bookPage.isTitleContaining("1984"));

        String pageTitle = bookPage.getPageTitle();

        assertEquals("1984 by George Orwell | Goodreads", pageTitle);
    }
}
