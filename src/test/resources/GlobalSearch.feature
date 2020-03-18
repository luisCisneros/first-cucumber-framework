Feature: Global Search on Landing Page

  Scenario: Search product by keyword
    Given User is on landing page
    When User enters "RTX 2080 Ti" on the global search bar
    Then At least one result should be shown on Results Page

  Scenario: Search product by model number
    Given User is on landing page
    When User enters "11G-P4-2281-KR" on the global search bar
    Then At least one result should be shown on Results Page

  Scenario: Search product by item number
    Given User is on landing page
    When User enters "N82E16814487418" on the global search bar
    Then Product Page should be displayed