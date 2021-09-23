# change-calculator-api
API to calculate change based on value of bill passed

API exposed /change-calculator
will expect 2 parameters, bill amount and user requests most amount of coins or least amount of coins.

Swagger page to access : http://localhost:8081/swagger-ui.html

Requirements:
• Available bills are (1, 2, 5, 10, 20, 50, 100)
• Available coins are (0.01, 0.05, 0.10, 0.25)
• Start with 100 coins of each type
• Change should be made by utilizing the least amount of coins
• API should validate bad input and respond accordingly
• Service should respond with an appropriate message if it does not have enough coins to make change
• The service should maintain the state of the coins throughout the transactions
• Deliver the code with test cases
• Upload your code to Github and come to interview prepared to walk through code


Bonus:
• Allow for number of coins to be configurable and easily changed
• Allow for the user to request for the most amount of coins to make change

Notes: 

Bonus points are also implemented. 
The number of coins are configurable in properties file and right now they are set to 100 and then can be updated easily if needed.
Most number of coins can be returned by passing "MostAmountOfCoins" as request param.
Least number of coins can be returned by passing "leastAmountOfCoins" as request param

Sample response when change is calculated
{
"Penny": 0,
"Quarter": 40,
"Nickle": 0,
"Dime": 0
}

Error response, when there are insufficient coins:
{
"message": "Insufficient coins!!",
"details": [
"Insufficient coins!!"
]
}

validations error when the bill values passed is not valid:
{
"message": "changeCalculator.billAmount: Field value should be from list of [1, 2, 5, 10, 20, 50, 100]",
"details": [
"changeCalculator.billAmount: Field value should be from list of [1, 2, 5, 10, 20, 50, 100]"
]
}