@retrieveBookingNonExistingBooking

Feature: Retrieve booking negative scenarios with non existing booking
  Scenario: Try to retrieve booking details with an invalid booking id
    Given booking id does not exist
    When I want to retrieve non existing booking
    Then I should not be able to retrieve my booking details