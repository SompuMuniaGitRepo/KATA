@createBookingScenariosInvalidLongPhone

Feature: Create booking negative scenarios with longer phone number
  Scenario: phone number is longer than 21 characters
    Given phone number is longer than 21 characters
    When I want to book the room with invalid long phone number
    Then Booking creation should fail with long phone number error message size between 11 to 21