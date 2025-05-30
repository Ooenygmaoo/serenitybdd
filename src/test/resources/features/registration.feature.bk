Feature: Registration


  Scenario Outline: Succesfull Registration

    Given Pepito wants to sign in the application
    When  Pepito sends the required information to sign up
      | <name> | <last name> | <age> | <email> | <country> |
    Then  Pepito should have a new account created
    Examples:

      | name   | last name | age | email                  | country  |
      | David  | Lopez     | 34  | davidzepol@hotmail.com | Colombia |
      | Vegeta | Lopez     | 20  | vegeta@hotmail.com     | Colombia |


  Scenario: Missing requiered fields for Registration

    Given Pepito wants to sign in the application
    When Pepito does not send the required information
    Then  Pepito should be told all fields are required


