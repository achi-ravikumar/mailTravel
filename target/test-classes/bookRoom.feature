@ui
Feature: Book a Double room

  Scenario Outline: Booking Double Room
    Given I search with "<SearchWord>"
    When I click on More Info on "<product>"
    Then I should see number of days is "<days>" days
    And I should see price "<price>"
    And I should see contact number is displayed
    When I click on Book Online button
    And I selected Double room
    And I entered below passenger details for "Adult 1"
      | Title | FirstName | LastName | DOB      |
      | Mr    | TestFN    | TestLN   | 1/1/1990 |
    And I entered below passenger details for "Adult 2"
      | Title | FirstName | LastName | DOB      |
      | Mr    | TestFN    | TestLN   | 1/2/1990 |
    And I ented below Lead contact details
      | MobinePhoneNumber | EmailAddress  | AddressLine1 | AddressLine2 | City   | PostCode |
      | 07944498873       | test@test.com | Add1         | add2         | London | XX6 7XX  |
    Then I should see expected price for Total Tour Cost for two passengers

    Examples:
      | SearchWord | product                          | days | price  |
      | India      | 11 Days - Classic Escorted Tours | 11   | £1,499 |




