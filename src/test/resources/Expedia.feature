@expedia
Feature: Trip search
#Como diferenciar si en dos features diferentes existe un step igual. Por ejemplo el primero de landing page
  Scenario: Search for a one way flight
    Given User is on expedia landing page
    When User selects single flight
    And User enters the following details:
    | origin                                                                  | destination                                                           | date        |
    | Guadalajara, Jalisco, Mexico (GDL-Don Miguel Hidalgo y Costilla Intl.)  | Cancun, Quintana Roo, Mexico (CUN-Aeropuerto Internacional de Cancun) | 05/22/2020  |
    Then Search results should be displayed