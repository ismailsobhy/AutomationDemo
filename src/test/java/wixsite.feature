Feature: wixsite

  Scenario: user buys items
    Given user is on homepage
    When user goes to shop and selects product
    When user adds a black item to cart
    When user adds a black item to cart
    When user clicks on add to cart
    Then total price will be 75 canadian dollars
    And user will click on checkout
