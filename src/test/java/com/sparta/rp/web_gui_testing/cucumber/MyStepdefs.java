package com.sparta.rp.web_gui_testing.cucumber;
import com.sparta.rp.web_gui_testing.HNpom.POMUtils;
import com.sparta.rp.web_gui_testing.HNpom.pages.CommentsPage;
import com.sparta.rp.web_gui_testing.HNpom.pages.HomePage;
import com.sparta.rp.web_gui_testing.HNpom.pages.PastPage;
import com.sparta.rp.web_gui_testing.HNpom.pages.QueryPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;

public class MyStepdefs {
    private WebDriver driver;
    private HomePage homePage;
    private CommentsPage commentsPage;
    private PastPage pastPage;

    private QueryPage queryPage;

    //Before, After, BeforeAll, AfterAll
    @BeforeAll
    void setupAll() {
        POMUtils.setDriverLocation("src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        
    }
    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        
        homePage.goToHomePage();
    }

    @When("I click on the comments link")
    public void iClickOnTheCommentsLink() {
        commentsPage = homePage.goToCommentsPage();
    }

    @Then("I will go to the comments page")
    public void iWillGoToTheCommentsPage() {
        Assertions.assertEquals("https://news.ycombinator.com/newcomments", commentsPage.getURL());
    }

    @When("I click on the pasts link")
    public void iClickOnThePastsLink() {
        pastPage = homePage.goToPastPage();

    }

    @Then("I will see yesterdays date")
    public void iWillSeeYesterdaysDate() {

        Assertions.assertEquals(LocalDate.now().minusDays(1).toString(),pastPage.getDateFromNavBar());
    }

    @Then("I will go to the pasts page")
    public void iWillGoToThePastsPage() {
        Assertions.assertEquals("https://news.ycombinator.com/front", pastPage.getURL());
    }

    @When("I search for python")
    public void iSearchForPython() {
      queryPage= homePage.searchInSearchBar("python");
    }

    @Then("I will go to the search page for python")
    public void iWillGoToTheSearchPageForPython() {
        Assertions.assertEquals("https://hn.algolia.com/?q=python",queryPage.getURL());
    }
}
