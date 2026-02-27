@retrieveBooking

Feature: Retrieve booking
  Scenario: Get back an existing booking details for a valid booking id
    When I want to retrieve my booking
    Then I should retrieve my booking details