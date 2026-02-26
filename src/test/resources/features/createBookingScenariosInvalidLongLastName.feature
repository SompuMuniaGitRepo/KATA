@createBookingScenariosInvalidLongLastName

Feature: Create booking negative scenarios with longer lastName
  Scenario: lastName is longer
    Given lastName is longer than 30 character
    When I want to book the room with invalid long lastName
    Then Booking creation should fail with long lastName error message size between 3 to 30