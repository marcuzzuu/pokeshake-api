# PokeShake API
A Pokemon API in a Shakespearean flavour.

# Endpoint

Get pokemon description in a Shakespearean dialect.

`GET /pokemon/{name}` 

# Documentation

API documentation via Swagger page at following address: 

`localhost:5000/swagger-ui.html`

**Application must be running in order to access documentation page.**

# Prerequisites

Docker, as well as docker-compose must be installed on your machine. 

You can find more instructions on how to do it here:
* [Docker-desktop](https://www.docker.com/products/docker-desktop)
* [Docker-compose](https://docs.docker.com/compose/install/)

# How to run it
### Docker
Browse to the root folder of the project `./pokeshake-api` and then run the following command:
 
`docker-compose up`

Application should now start listening on `localhost:5000`

**You might need to login to your docker account to be able to download the image.**

# Run tests

Browse to the root folder of the project `./pokeshake-api` and then run the following command:

`./mvnw clean test`

# Underlying APIs

This project uses the external APIs:

* [Poke-API](https://pokeapi.co/) (100 requests per minute).
* [FunTranslations-API](https://funtranslations.com/api/shakespeare) (5 requests per hour - 60 per day).



