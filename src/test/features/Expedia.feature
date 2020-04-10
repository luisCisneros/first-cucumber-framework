@expedia
Feature: Search Trips on Expedia Landing page

  Scenario: Search for a one way flight
    Given user is on "Expedia" landing page
    When user selects single flight
    And user enters the following details:
    | origin      | Guadalajara, Jalisco, Mexico (GDL-Don Miguel Hidalgo y Costilla Intl.) |
    | destination | Cancun, Quintana Roo, Mexico (CUN-Aeropuerto Internacional de Cancun)  |
    | date        | 10/22/2020                                                             |
    Then search results should be displayed
