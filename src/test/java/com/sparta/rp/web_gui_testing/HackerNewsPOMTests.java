package com.sparta.rp.web_gui_testing;

import com.sparta.rp.web_gui_testing.HNpom.DriverOptions;
import com.sparta.rp.web_gui_testing.HNpom.POMUtils;
import com.sparta.rp.web_gui_testing.HNpom.pages.CommentsPage;
import com.sparta.rp.web_gui_testing.HNpom.pages.DriverFactory;
import com.sparta.rp.web_gui_testing.HNpom.pages.HomePage;
import com.sparta.rp.web_gui_testing.HNpom.pages.PastPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;
import java.time.LocalDate;
import java.util.List;

public class HackerNewsPOMTests {

    private static WebDriver driver;
    private HomePage homePage;
    private CommentsPage commentsPage;
    private PastPage pastPage;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

    @BeforeAll
    static void setupAll() {
        POMUtils.setDriverLocation(DRIVER_LOCATION);
    }

    @BeforeEach
    void setup() {
        driver = DriverFactory.getDriver(DriverOptions.CHROME);
       // driver = new ChromeDriver();
        homePage = new HomePage(driver);

    }

    @AfterAll
    static void tearDownAll() {
        driver.quit();
    }

    @Test
    @DisplayName("Check that we can go to the comments page")
    void checkThatWeCanGoToTheCommentsPage() {
        String url = homePage.goToHomePage().goToCommentsPage().getURL();
        Assertions.assertEquals("https://news.ycombinator.com/newcomments", url);
    }

    @Test
    @DisplayName("Check that the class page has yesterday's date")
    void checkThatTheClassPageHasYesterdaySDate() {
        String dateOnPastPage = homePage.goToPastPage().getDateFromNavBar();
        String yesterdaysDate = LocalDate.now().minusDays(1).toString();
        Assertions.assertEquals(yesterdaysDate, dateOnPastPage);

    }

    @Test
    @DisplayName("Check that the all the posts on the homepage display scores")
    void checkThatTheAllThePostsOnTheHomepageDisplayScores() {
        List<WebElement> scores = homePage.getScores();
        boolean answer = true;
        for (WebElement score : scores) {
            if (!score.getText().matches("[0-9]+ points")) {
                answer = false;
                break;
            }
        }
        Assertions.assertTrue(answer);
    }

    @Nested
    class RanksTests{

        @Test
        @DisplayName("Check that the homepage posts are displayed in ranked order")
        void checkThatTheHomepagePostsAreInRankedOrder() {
            Assertions.assertTrue(homePage.checkRanksAreInOrder());
        }

        @Test
        @DisplayName("Check that the past page posts are displayed in ranked order")
        void checkThatThePastPagePostsAreInRankedOrder() {
            Assertions.assertTrue(homePage.goToPastPage().checkRanksAreInOrder());
        }

        @Test
        @DisplayName("Check that show page posts are displayed in ranked order")
        void checkThatShowPagePostsAreInRankedOrder() {
            Assertions.assertTrue(homePage.goToShowPage().checkRanksAreInOrder());

        }
    }

    @Nested
    class SearchBarTests{
        @Test
        @DisplayName("Check that you can search from the homepage")
        void checkThatYouCanSearchFromTheHomepage() {
            String url = homePage.searchInSearchBar("java").getURL();
            Assertions.assertEquals("https://hn.algolia.com/?q=java", url);

        }
        @Test
        @DisplayName("Check that you can search from the comments page")
        void checkThatYouCanSearchFromTheCommentsPage() {
            String url = homePage.goToCommentsPage().searchInSearchBar("java").getURL();
            Assertions.assertEquals("https://hn.algolia.com/?q=java", url);
        }

        @Test
        @DisplayName("Check that you can search from the past page")
        void checkThatYouCanSearchFromThePastPage() {
            String url = homePage.goToPastPage().searchInSearchBar("java").getURL();
            Assertions.assertEquals("https://hn.algolia.com/?q=java", url);
        }

        @Test
        @DisplayName("Check that you can search from the show page")
        void checkThatYouCanSearchFromTheShowPage() {
            String url = homePage.goToShowPage().searchInSearchBar("java").getURL();
            Assertions.assertEquals("https://hn.algolia.com/?q=java", url);
        }
    }
}
