@createBookingScenariosInvalidShortLastName

Feature: Create booking negative scenarios with shorter lastName
  Scenario: lastName is shorter
    Given lastName is shorter than 3 character
    When I want to book the room with invalid short lastName
    Then Booking creation should fail with short lastName error message size between 3 to 30