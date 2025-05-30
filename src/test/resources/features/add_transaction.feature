Feature: Add transaction

  Scenario:  add new transaction


    Given Pepito is logged into the application

      | user | pass |

    When  Pepito enters the required information for the new transaction


      | 12-12-25 | 500 |Testing Description|
    Then  Pepito should see a new transaction in the transaction list with correct details
