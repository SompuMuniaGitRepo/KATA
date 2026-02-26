@retrieveBookingNonExistingBooking

Feature: Retrieve booking negative scenarios with non existing booking
  Scenario: Booking does not exist
    Given booking id does not exist
    When I want to retrieve non existing booking
    Then I should not be able to retrieve my booking details