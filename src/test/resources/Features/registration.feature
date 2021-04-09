Feature: Registration of a mail account
  I want to create different scenarios to secure correct error handling when I create different mail accounts 
  which some are not correct

  Scenario Outline: Create a Mailchimp account
  

  	
    Given I add my <email> to create an Mialchimp account
    And I also add <username> to create my account
    And I enter password 
    When I click on the Sign-up button
    Then The account creation shold be <status>

    Examples: 
     |email							 |username				    |status             |
     |"myEmail@test.com" |"randomusername"    |"success"          |	
     |"myEmail@test.com" |"onehundredusername"|"toManyCharacters" |
     |"myEmail@test.com" |"occupiedusername"  |"occupied"         |
     |" " 							  |"randomusername"   |"forgotEmail"      |

    	
