Feature: Adopting Puppies

  Scenario: A puppy finds a new home
    Given the following puppies are available for adoption:
        | Puppy Name |
        | Brook      |
        | Hanna      |
        | Maggie Mae |
        | Ruby Sue   |
    And the following know registered owners:
        | Owner Name |
        | Mary       |
        | Peter      |
    When customer "Pat" requests to adopt puppy "Hanna"
    Then "Pat" is shown a confirmation message
    And "Hanna" is no longer listed as available
    And "Pat" is listed as registered owner

