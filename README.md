# PokeShake API
A Pokemon API in a Shakespearean flavour.

# Endpoint
`GET /pokemon/{name}` 

Get pokemon description in a Shakespearean dialect.

# Prerequisites

Docker must be installed on your machine. 
You can find more instructions on how to do it at:

`https://www.docker.com/get-started`

# Run with Docker

Browse to the root folder of the project `./pokeshake-api` and then run the following command:
 
`docker-compose up`

Application should now start listening on `localhost:5000`

# Run tests

Browse to the root folder of the project `./pokeshake-api` and then run the following command:

`mvnw clean test`

# Underlying APIs

This project uses the external APIs:

* https://pokeapi.co/docs/v2.html/ (100 requests per minute).
* https://funtranslations.com/api/shakespeare (5 requests per hour - 60 per day).



