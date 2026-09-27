Feature: SauceDemo Login

  @login @negative
Scenario Outline: Login with locked user
Given user is on the SauceDemo login page
When user enters username "<username>"
And user enters password "<password>"
And user clicks on the login button
Then user should remain on the login page
And login error should be displayed

Examples:
| username          | password     |
| locked_out_user   | secret_sauce |