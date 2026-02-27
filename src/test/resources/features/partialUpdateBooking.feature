@partialUpdateBooking

Feature: Partially update booking
  Scenario: Update few details of an existing booking
    When I want to partially update my booking
    Then I should get my partially updated booking details