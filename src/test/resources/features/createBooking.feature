@createBooking

Feature: Create booking
  Scenario: Create a new booking with valid data
    When I want to book the room
    Then I should retrieve my booking