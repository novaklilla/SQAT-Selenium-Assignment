import PageObjects.AuthenticationPage;

import org.junit.*;
import static org.junit.Assert.*;

public class AuthenticationTest extends BaseSeleniumTest {
    private final String email = "seleniumtest519@gmail.com";
    private final String password = "SH65*X84Be";
    private String webPageSignIn = "https://www.goodreads.com/user/sign_in";
    private AuthenticationPage authenticationPage;

    @Before
    public void setUp() {
        authenticationPage = new AuthenticationPage(driver, wait);
    }

    @Test
    public void testLogin() {
        driver.get(webPageSignIn);
        authenticationPage.signIn(email, password);
        assertTrue("Currently Reading header is visible, meaning you are logged in", authenticationPage.isCurrentlyReadingHeaderDisplayed());
    }

    @Test
    public void testLoginAndLogout() {
        driver.get(webPageSignIn);
        authenticationPage.signIn(email, password);
        authenticationPage.signOut();
        assertTrue("Sign In text is displayed, meaning you are logged out", authenticationPage.isSignInTextDisplayed());
    }
}
