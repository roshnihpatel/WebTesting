package com.sparta.rp.web_gui_testing;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class HackerNewsTests {
    static WebDriver driver;

    @BeforeAll
    static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeEach
    void setup() {
        driver.get("https://news.ycombinator.com/");
    }

    @AfterAll
    static void tearDownAll() {
        driver.quit();
    }

    @Test
    @DisplayName("Check that the URL goes to HN homepage")
    void checkThatTheUrlGoesToHnHomepage() {
        Assertions.assertEquals("https://news.ycombinator.com/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check that the new link works")
    void checkThatTheNewLinkWorks() {
        driver.findElement(By.linkText("new")).click();
        Assertions.assertEquals("https://news.ycombinator.com/newest", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check that we can search using Java keyword")
    void checkThatWeCanSearchUsingJavaKeyword() {
        driver.findElement(By.name("q")).sendKeys("Java", Keys.ENTER);
        Assertions.assertEquals("https://hn.algolia.com/?q=Java", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check that when past is clicked, yesterdays date is displayed")
    void checkThatWhenPastIsClickedYesterdaysDateIsDisplayed() {
        driver.findElement(By.linkText("past")).click();
        String expected = LocalDate.now().minusDays(1).toString();
        String answer = driver.findElement(By.className("pagetop")).findElement(By.tagName("font")).getText();
        Assertions.assertEquals(expected, answer);
    }

    @Test
    @DisplayName("Check we can open a link in a new tab")
    void checkWeCanOpenALinkInANewTab() {
        String originalTab = driver.getWindowHandle();
        System.out.println(originalTab);
        driver.findElement(By.linkText("new")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        Set<String> windowHandles = driver.getWindowHandles();
        for (String tab : windowHandles) {
            if (!originalTab.equals(tab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        Assertions.assertNotEquals(driver.getWindowHandle(), originalTab);
    }

    @Test
    @DisplayName("Check that each post has the number of points displayed")
    void checkThatEachPostHasTheNumberOfPointsDisplayed() {
        List<WebElement> scores = driver.findElements(By.className("score"));
        boolean answer = true;
        for (WebElement score : scores) {
            if (!score.getText().matches("[0-9]+ points")) {
                answer = false;
                break;
            }
        }
        Assertions.assertTrue(answer);
    }

    @Test
    @DisplayName("Check that each post shows the age of the post")
    void checkThatEachPostShowsTheAgeOfThePost() {
        List<WebElement> subtexts = driver.findElements(By.className("subtext"));
        boolean answer = true;
        for (WebElement age : subtexts) {
            if (!age.findElement(By.className("age")).findElement(By.tagName("a")).getText().matches("[0-9]+ (hour|day|days|minute)s? ago")) {
                answer = false;
                break;
            }
        }
        Assertions.assertTrue(answer);
    }

    @Test
    @DisplayName("Check that all posts on the first page contains Java when Java is searched")
    void checkThatAllPostsContainsJavaWhenJavaIsSearched() {
        driver.findElement(By.name("q")).sendKeys("Java", Keys.ENTER);
        List<WebElement> storyTitles = driver.findElements(By.className("Story_title"));
        System.out.println(storyTitles.size());
        boolean answer = true;
        for (WebElement storyTitle : storyTitles) {
            if (!storyTitle.findElement(By.tagName("span")).getText().toLowerCase().contains("java")) {
                answer = false;
                break;
            }
        }
        Assertions.assertTrue(answer);
    }

    @Test
    @DisplayName("Check that when on a searched paged, clicking the logo returns to the homepage")
    void checkThatWhenOnASearchedPagedClickingTheLogoReturnsToTheHomepage() {
        driver.findElement(By.name("q")).sendKeys("Java", Keys.ENTER);
        driver.findElement(By.className("SearchHeader_logo")).findElement(By.tagName("a")).click();
        Assertions.assertEquals("https://news.ycombinator.com/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check that each post on the home page is numbered in order")
    void checkThatEachPostOnTheHomePageIsNumberedInOrder() {
        List<WebElement> ranks = driver.findElements(By.className("rank"));
        boolean answer = true;
        for (int i = 0; i < ranks.size(); i++) {
            if (!ranks.get(i).getText().matches(i + 1 + ".")) {
                answer = false;
                break;
            }
        }
        Assertions.assertTrue(answer);
    }
}
