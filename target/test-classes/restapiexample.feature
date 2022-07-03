@api

Feature: API

  Scenario: Create Employee
    When I create a Employee with below details
      | name  | salary | age |
      | test1 | 1000   | 40  |
    Then I should see below response data for Create employee
      | status  | name  | salary | age |
      | success | test1 | 1000   | 40  |

  Scenario: Get Employee
    When I fetch a Employee with id "1"
    Then I should see below response data for Get Request
      | status  | id | employee_name | employee_salary | employee_age | profile_image |
      | success | 1  | Tiger Nixon   | 320800          | 61           |               |

  Scenario:  Delete Employee
    When I delete a Employee with id "1"
    Then I should see below response data for Delete Request
      | status  | data | message                               |
      | success | 1    | Successfully! Record has been deleted |
