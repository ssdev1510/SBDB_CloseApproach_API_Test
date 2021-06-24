Feature: Validation of SBDB Close-Approach Data API.

Description: This feature file is to verify the signature, source & other Data of response payload.


@GET @SMOKE
Scenario: Verify the status code and Response time of DATA API response.
Given User set basePath URL "/cad.api"
When User calls "GET" operation with https request
Then User should get status code "200"
And Response time should be less than "10" second

@GET 
Scenario: Verify the Signature & Source of DATA API response.
Given User set basePath URL "/cad.api"
When User calls "GET" operation with https request
Then User should get status code "200"
And User should see booking Signature Version "1.3" and Source "NASA/JPL SBDB Close Approach Data API"


@GET
Scenario Outline: Verify the number of asteroids approached to earth between Minimum date & Maximum date.
Given User set basePath URL "/cad.api"
When User calls "GET" operation with query parameters Min Date "<date-min>" Max Date "<date-max>" Max Distance "<dist-max>"
Then User should get status code "200"
And User should see count as "<count>"

Examples:
	| date-min   | date-max   | dist-max | count  |
	| 2010-01-01 | 2011-01-01 | 1LD      | 23     |
	| 2018-01-01 | 2020-01-01 | 1LD      | 162     |
	


@GET
Scenario: Verify when query is too restrictive > Count should be come as 0.
Given User set basePath URL "/cad.api"
When User calls "GET" operation with query parameters
	| des | date-min   | dist-max |
	| 433 | 2018-01-01 | 10LD     |
Then User should get status code "200"
And User should see count as "0"


@GET
Scenario: Verify the orbit Id of asteroid primary designation 433 in year 1900.
Given User set basePath URL "/cad.api"
When User calls "GET" operation with query parameters with asteroid primary designation
	| des | date-min   | date-max   | dist-max  |
	| 433 | 1990-01-01 | 1990-01-01 | 100LD     |
Then User should get status code "200"
And User should see Orbit ID as "659"


#Negative Scenario > Input wrong date in date field
@GET @NEGATIVE
Scenario: Verify the output when user pass wrong value in data field.
Given User set basePath URL "/cad.api"
When User calls "GET" operation with query parameters
	| des | date-min    | dist-max |
	| 433 | 2018-01-011 | 10LD     |
Then User should get status code "400"



#Uncomment below scenario if want to view failed scenario in test result report.
#GET @NEGATIVE
#Scenario: Intentionally Failing the scenario to view failed test result in report.
#Given User set basePath URL "/cad.api"
#When User calls "GET" operation with query parameters
#	| des | date-min    | dist-max |
#	| 433 | 2018-01-011 | 10LD     |
#Then User should get status code "2000"



