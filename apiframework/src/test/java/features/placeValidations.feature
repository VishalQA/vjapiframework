Feature: Validate the Place API

Scenario: Verify if place is added
Given Add place payload
When call "addplaceAPI" with POST method
Then API succesfull with status code 200
And "status" is "OK"
And "scope" is "APP"


