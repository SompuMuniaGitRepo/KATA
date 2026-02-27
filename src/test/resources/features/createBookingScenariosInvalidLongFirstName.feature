@createBookingScenariosInvalidLongFirstName

Feature: Create booking negative scenarios with longer firstName
  Scenario: firstName is longer than 18 characters
    Given firstName is longer than 18 characters
    When I want to book the room with invalid long firstName
    Then Booking creation should fail with long firstName error message size between 3 to 18