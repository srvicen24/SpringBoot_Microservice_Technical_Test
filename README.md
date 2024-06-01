# Mercadona Product Service

## Overview

This repository contains the solution for a technical test for Mercadona. The project is a RESTful API that allows to
manage products. It uses various technologies and tools including Docker, Flyway, Caffeine, and Spring Boot.

## Prerequisites

- [Java 21 or higher](https://www.oracle.com/es/java/technologies/downloads/#jdk22-windows)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes.

### Installation

1. Clone the repository:

```sh
git clone https://github.com/srvicen24/SuperMarket_Technical_Test.git
```

2. Navigate into the cloned repository:

```sh
cd SuperMarket_Technical_Test
```

3. Use Maven to install dependencies, build the project and run the tests:

```sh
./mvn clean install
```

### Docker

A Dockerfile and docker-compose.yml is provided. To build and run the Docker Compose, use the following commands:

```sh
docker-compose down
docker build -t mercadona-product-service .
docker-compose up -d
```

### Usage

The API endpoints are accessible at `http://localhost:8081/api/products` for products
and `http://localhost:8081/api/supplier` for suppliers. The OpenAPI documentation can be viewed
at `http://localhost:8081/swagger-ui.html`. Essentially, the API allows you to perform CRUD operations for managing both
products and suppliers. For products, you can use either the EAN or ID as identifiers, and for suppliers, you can use
either the Reference or ID.

Also, you can find a Postman collection in the root of the project.

## Project Review

### Architecture

The architecture of this project is based on the `Hexagonal Architecture` (also known as Ports and Adapters) and follows
the principles of Domain-Driven Design (`DDD`) and Test-Driven Development (`TDD`).

The project is divided into the following main packages:

- `infrastructure`: Contains the classes that adapt the domain to the external world. This package contains the REST
  controllers, the Spring Boot configuration classes, the JPA repositories and Entity classes.
- `application`: Contains the application use cases that orchestrate the domain objects and the repositories.
- `domain`: Contains the domain objects and the business logic, including the validations, value objects, and domain
  services.

The project is implemented following the `SOLID` principles, trying to keep the code as clean and maintainable as
possible.

### Testing

I used `TDD` to develop this project, so the tests were written before the implementation. The tests are written using
JUnit 5 and Mockito.

### Database

The project uses Postgres SQL database for development and an in-memory H2 for testing purposes. The database is
initialized using
Flyway, which is a database migration tool that allows you to manage your database schema using code. Furthermore, the
Database is deployed using Docker.

### Git

The methodology used to develop this project is GitFlow. The project has two main branches: `main` and `develop`, in
addition, I have created several `feature` branches throughout the development
process.

Although it is generally good practice to delete these `feature` branches once they have been merged back into
the `develop` or `main` branch, I have chosen to keep them. This decision was made to provide a clear and detailed
history of the development process and the methodology followed.

### Caching

The project uses Caffeine as a caching library to cache the products and suppliers in memory.

### Logging

The project uses Logback as the logging framework. The logs are written to the console and to a file.

### Error Handling

The project uses a custom exception handler to handle exceptions and return a proper response to the client. It works
using custom exceptions.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
