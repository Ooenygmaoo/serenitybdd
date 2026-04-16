@webMuthematrix
Feature:  Votation

  @windows
  Scenario: David votes for the server on Windows
    Given David enter to the muthematrix page
    When David enter to the login page and click on vote with username "slash17" and password "nirvana16"
    Then David Could be vote for the server

  @mac
  Scenario: Enzo votes for the server on Mac
    Given Enzo enter to the muthematrix page
    When Enzo enter to the login page and click on vote with username "enzo" and password "alejandro1991"
    Then Enzo Could be vote for the server

