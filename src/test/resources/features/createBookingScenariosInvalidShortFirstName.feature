@createBookingScenariosInvalidShortFirstName

Feature: Create booking negative scenarios with shorter firstName
  Scenario: firstName is shorter than 3 characters
    Given firstName is shorter than 3 characters
    When I want to book the room with invalid short firstName
    Then Booking creation should fail with short firstName error message size between 3 to 18