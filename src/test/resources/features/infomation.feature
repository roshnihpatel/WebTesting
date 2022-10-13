Ability: Able to see appropriate data on each page
  Scenario:  Checking the date on the pasts page
    Given I am on the homepage
    When I click on the pasts link
    Then I will see yesterdays date

    Scenario Outline: Navigation between <startPage> and <location>
      Given I am on the "startPage"
      When I click on the "location" link
      Then I will to the "location" page

      Examples:
