@createBookingScenariosInvalidEmail

Feature: Create booking negative scenarios with invalid email
  Scenario: email is invalid
    Given email is malformed email
    When I want to book the room with invalid email
    Then Booking creation should fail with invalid email error message well-formed email