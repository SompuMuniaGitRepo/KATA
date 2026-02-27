@createBookingScenariosInvalidLongLastName

Feature: Create booking negative scenarios with longer lastName
  Scenario: lastName is longer than 18 characters
    Given lastName is longer than 18 characters
    When I want to book the room with invalid long lastName
    Then Booking creation should fail with long lastName error message size between 3 to 18