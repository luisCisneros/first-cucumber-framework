@newegg
Feature: Global Search on Newegg Landing Page

  Scenario: Search product by keyword
    Given user is on "Newegg" landing page
    When user enters "RTX 2080 Ti" on the global search bar
    Then at least one result should be shown on Results Page

  Scenario: Search product by model number
    Given user is on "Newegg" landing page
    When user enters "11G-P4-2281-KR" on the global search bar
    Then at least one result should be shown on Results Page

    @long
  Scenario Outline: Search product by item number
    Given user is on "Newegg" landing page
    When user enters "<itemNumber>" on the global search bar
    Then Product Page should be displayed and "<itemNumber>", "<productName>" must be present

      Examples:
    | itemNumber      | productName       |
    | N82E16814487418 | RTX 2080 Ti       |
    | N82E16820147704 | SAMSUNG 256GB BAR |
    | 9SIA378B540397  | Roku Ultra HD     |

    @test
  Scenario: Search for a product that does not exist on the store
    Given user is on "Newegg" landing page
    When user enters "intel" on the global search bar
    Then no search results should be displayed
#   I'm failing this on purpose in order to be able to see the screenshot on the cucumber report.
#   Intel products DO exist on the store.