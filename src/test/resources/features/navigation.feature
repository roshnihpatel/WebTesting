@SimpleTests
Feature: Navigate through different webpages

  Background: Starting from the homepage
  Given I am on the homepage

  Scenario: Going to the comments page from the homepage
    When I click on the comments link
    Then I will go to the comments page

    Scenario: Going to the pasts page from the homepage

      When I click on the pasts link
      Then I will go to the pasts page

      Scenario: Going to the search page

        When I search for python
        Then I will go to the search page for python