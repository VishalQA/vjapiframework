Feature: Validate the Place API

Scenario Outline: Verify if place is added
Given Add place payload with "<name>" "<language>" "<address>"
When call "addplaceAPI" with "POST" method
Then API succesfull with status code 200
And "status" is "OK"
And "scope" is "APP"


Examples:
         |name      |language     |address         | 
         |newname   |English      |Tower one       |
         |nametwo   |Spanish      |Tower two       |
