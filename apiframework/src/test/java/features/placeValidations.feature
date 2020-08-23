Feature: Validate the Place API
 @AddPlace @Regression
Scenario Outline: Verify if place is added
Given Add place payload with "<name>" "<language>" "<address>"
When call "addplaceAPI" with "POST" method
Then API succesfull with status code 200
And "status" is "OK"
And "scope" is "APP"
And verify place_id maps to "<name>" using "getplaceAPI"


Examples:
         |name      |language     |address         | 
         |nametwo   |English      |Tower one       |
#        |nametwo   |Spanish      |Tower two       |


@DeletePlace @Regression
Scenario Outline: Verify if place is deleted

Given Delete place payload with 
When call "deleteplaceAPI" with "POST" method
Then API succesfull with status code 200
And "status" is "OK"