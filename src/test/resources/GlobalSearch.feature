Feature: Global Search on Landing Page

  Scenario: Search product by keyword
    Given User is on landing page
    When User enters "RTX 2080 Ti" on the global search bar
    Then At least one result should be shown on Results Page

  Scenario: Search product by model number
    Given User is on landing page
    When User enters "11G-P4-2281-KR" on the global search bar
    Then At least one result should be shown on Results Page

  Scenario Outline: Search product by item number
    Given User is on landing page
    When User enters "<itemNumber>" on the global search bar
    Then Product Page should be displayed and "<itemNumber>", "<productName>" must be present

      Examples: #The variables didn't work without the double quotes. On Cucumber website is shown with only <>
    | itemNumber      | productName       |
    | N82E16814487418 | RTX 2080 Ti       |
    | N82E16820147704 | SAMSUNG 256GB BAR |
    | 9SIA378B540397  | Roku Ultra HD     |

  @test
  Scenario: Search for a product that does not exist on the store
    Given User is on landing page
    When User enters "chilaquiles" on the global search bar
    Then No search results message should be displayed