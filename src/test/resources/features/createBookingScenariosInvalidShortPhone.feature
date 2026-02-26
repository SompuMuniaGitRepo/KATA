@createBookingScenariosInvalidShortPhone

Feature: Create booking negative scenarios with shorter phone number
  Scenario: phone number is shorter
    Given phone number is shorter than 3 character
    When I want to book the room with invalid short phone number
    Then Booking creation should fail with short phone number error message size between 11 to 21