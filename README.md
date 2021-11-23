# E-Wallet

Assume that credits are integers only. In real world applications, money is represented as the smallest unit in the currency, eg. cents, so as to avoid using floats. 

## Database Schema
#Table 1: account
id, email, balance

#Table 2: transaction
id, type, sender_email, receiver_email, amount, datetime

*note that for this use case, the only type is "transfer". In future, if we want to support other transaction types, we need to create seperate tables for each type of transactions.


## APIs
### User Story 1- to register an account-  /POST http://cryptic-ridge-89057.herokuapp.com/api/accounts
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
} if account is already registered

### User Story 2- to check balance-  /POST http://cryptic-ridge-89057.herokuapp.com/api/balance
##### sample request body
{
    "email": "x@gmail.com"
}

#### sample response
{
    "balance": 9846,
    "success": true
} if successful

{
    "success": false,
    "error": "no account found"
} if there are no such account


### User Story 3- to transfer credits-  /POST http://cryptic-ridge-89057.herokuapp.com/api/transactions
##### sample request body
{
    "email": "x@gmail.com",
    "transferee": "y@gmail.com",
    "amount": 50
}

#### sample response
{
    "success": true,
} if successful

{
    "success": false,
    "error": "sender email is invalid",
    "status": "BAD_REQUEST"
} if sender does not exist, 

{
    "success": false,
    "error": "receiver email is invalid",
    "status": "BAD_REQUEST"
} if receiver does not exist, 

{
    "success": false,
    "error": "Insufficient balance",
    "status": "BAD_REQUEST"
} if balance is insufficient


### User Story 4- to obtain latest transactions- /POST http://127.0.0.1:8080/api/latest-transactions
##### sample request body
{
    "email": "x@gmail.com"
}

#### sample response
{
    "success": true,
    "transactions": [
        {
            "id": 8,
            "type": "transfer",
            "sender_email": "timothy@gmail.com",
            "receiver_email": "richard@gmail.com",
            "amount": 8,
            "datetime": "2021-11-23T04:59:39.000+00:00"
        }
    ]
}

