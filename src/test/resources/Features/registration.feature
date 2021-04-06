Feature: Registration of a mail account
  I want to create different scenarios to secure correct error handling when I create mail accounts 
  which are not correct

  Scenario Outline: Create a Mailchimp account
  	
    Given I add my <email> to create an Mialchimp account
    And I also add <username> to create my account
    And I enter password <password>
    When I click on the Sign-up button
    Then The account creation should be <status> for user <username>

    Examples: 
     	|email    	 | username       |password		 | status  					       |
   	  | "email1"   | "userid1"      |"password1" |"login" 						     |
      | "email2"   | "userid2"     	|"password2" |"100 characters long"    |
      | "email3"   | "userid3"      |"password3" |"Spooky."						     |
    	| "email4"   | "userid4"      |"password4" |"Please enter a value"   |
