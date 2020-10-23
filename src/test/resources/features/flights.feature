Feature: Check flights page

  Scenario: Check first 2 clusters are open and the rest is closed
    Given I navigate to Idealo Flights
    Then I see first 2 states are open and the rest are closed


  Scenario: Each cluster has at least 1 offer, but maximum 3 offers
    Given I navigate to Idealo Flights
    Then I see each cluster contain 1-3 offers

  Scenario: Total number of offers is no more than 30
    Given I navigate to Idealo Flights
    Then I see total number of offers is <=30
#
  Scenario: All clusters can be opened with offers are displayed and closed with offers are hidden
    Given I navigate to Idealo Flights
    Then I click expand button for each element and check state of offers