Feature: listing topics

  Scenario: Listing topics must return a list
    Given a topic with name "test" is created
     When topics are listed
     Then a topic with name "test" exists

