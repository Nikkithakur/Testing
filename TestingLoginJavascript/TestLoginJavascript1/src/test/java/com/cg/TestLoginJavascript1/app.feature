Feature: Login Page

Scenario: Succesfully Validating login fields
Given Logging in with username and password
When Enter username as "Nikhilprasadthakur@gmail.com" and password as "Nikhil"
Then Display success page

Scenario: Invalid email 
Given Logging in with username and password
When Enter wrong username as "Nikhilprasad" and password as "Nikhil"
Then Display Alert box as invalid email

Scenario: Invalid Passowrd
Given Logging in with username and password
When Enter username as "Nikhilprasadthakur@gmail.com" and wrong password as "Nikhi"
Then Display Alert box as invalid password