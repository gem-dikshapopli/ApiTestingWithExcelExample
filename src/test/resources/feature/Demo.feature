@Demo1
Feature:Api Testing

  Scenario:Api Testing of restful-Booker

    Given Get the Authorization Token
    And Verify the Restful-booker site
    Then Get the Booking Id's
    And Get the Booking Data of The first id
    Then Create a new Booking and post it on the server
    Then Perform updation of the created booking data
    Then Perform the partial updation on the changed data
    And Delete the data
