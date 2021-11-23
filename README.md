# E-Wallet

Assume that credits are integers only. In real world applications, money is represented as the smallest unit in the currency, eg. cents, so as to avoid using floats. 

## Database Schema
#Table 1: account
id, email, balance

#Table 2: transaction
id, type, sender_email, receiver_email, amount, datetime

*note that for this use case, the only type is "transfer". In future, if we want to support other transaction types, we need to create seperate tables for each type of transactions.


## APIs
#### /POST http://cryptic-ridge-89057.herokuapp.com/api/accounts
##### sample request body
{
    "email": "x@gmail.com"
}

#### sample response
{
    "success": true,
} if successful

{
    "success": false,
    "error": "account already exists"
}

