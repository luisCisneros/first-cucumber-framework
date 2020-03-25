@expedia
Feature: Trip search
#Como diferenciar si en dos features diferentes existe un step igual. Por ejemplo el primero de landing page
  Scenario: Search for a one way flight
    Given user is on expedia landing page
    When user selects single flight
    And user enters the following details:
    | origin      | Guadalajara, Jalisco, Mexico (GDL-Don Miguel Hidalgo y Costilla Intl.) |
    | destination | Cancun, Quintana Roo, Mexico (CUN-Aeropuerto Internacional de Cancun)  |
    | date        | 05/22/2020                                                             |
    Then search results should be displayed