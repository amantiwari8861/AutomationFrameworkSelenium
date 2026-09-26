Feature: SauceDemo Login

  @login @regression
  Scenario Outline: Login with multiple credentials

    Given user is on the SauceDemo login page
    When user enters email "<username>"
    And user enters password "<password>"
    And user clicks on the login button
    Then user should be redirected to the inventory page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | locked_out_user         | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |