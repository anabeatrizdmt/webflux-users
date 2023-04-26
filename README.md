# WebFlux Users API

This project is a reactive RESTful API for managing users. It is written in Java and uses reactive programming with Spring WebFlux and MongoDB.

**Note:** This API is part of a larger e-commerce system that includes product catalog management and order management. To run the complete system, you must also run the [WebFlux Catalog API](https://github.com/anabeatrizdmt/webflux-catalog), the [WebFlux Orders API](https://github.com/anabeatrizdmt/webflux-orders), and the [WebFlux E-commerce API Gateway](https://github.com/anabeatrizdmt/webflux-ecommerce-api-gateway). The API Gateway provides a unified interface to the entire system and manages communication between the individual APIs.

## Requirements

- Java 11 or higher
- MongoDB

## Installation and Setup

1. Clone the repository:

```
git clone https://github.com/anabeatrizdmt/webflux-users.git
```

2. Install dependencies:

```
cd webflux-catalog
mvn install
```

3. Run the application:

```
mvn spring-boot:run
```


## Usage

### Create a user

To create a new user, send a `POST` request to `http://localhost:8080/users` with a JSON payload in the following format:

```
{
"document": "00000000000",
"email": "anaTrindade@email.com",
"password": "password"
}
```

### Get all users

To retrieve a list of all users, send a `GET` request to `http://localhost:8080/users`.

### Get a user by ID

To retrieve a specific user by their ID, send a `GET` request to `http://localhost:8080/users/{id}`, where `{id}` is the ID of the user you wish to retrieve.

### Check user status

To check the status of a user, send a `GET` request to `http://localhost:8080/users/status/{id}`, where `{id}` is the ID of the user you wish to check.
