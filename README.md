# FX Deals Data Warehouse

This project is a data warehouse for Bloomberg, designed to analyze FX deals. 
It allows users to submit FX deal details and persist them into a PostgreSQL database. 
The project is built using Quarkus, a Java framework for building cloud-native applications.

## Features

- Accepts FX deal details as a request with fields: Deal uniqueId, From Currency ISO Code, To Currency ISO Code, Deal timestamp, Deal amount in ordering currency.
- Retrieve the FX deals by id.
- Validates the row structure of the request.
- Ensures that the same request is not imported twice.
- Persists the valid FX deal requests into the PostgreSQL database.
- Provides a workable deployment with Docker Compose for easy setup.

## Prerequisites

Before running the project, ensure you have the following installed on your system:

- Java Development Kit (JDK) 11 or higher
- Maven 3.8.*
- Docker (with Docker Compose support) optional
- PostgreSQL database

## Getting Started

1. Clone the repository to your local machine.
2. Set up the PostgreSQL database with the required schema and credentials.
3. Configure the database connection in the `application.properties` file.
4. Build the project using Maven:

```bash
mvn clean package
```
## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

You need to copy the config directory to the same directory.
The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Create a docker image

To create and run a docker image :

Package the application:
```shell script
 ./mvnw package
 ```

Build a docker executable :

```shell script
 docker build -f src/main/docker/Dockerfile.jvm -t quarkus/fx-deals-importer .
```

Run the docker image :
```shell script
 docker run -i --rm -p 8080:8080 quarkus/fx-deals-importer
```

## Endpoints

### 1. Import Forex Deal - POST Method

Endpoint: `/rest/1.0/fxDeals/import`

This endpoint allows you to import a new forex deal by sending a POST request with the following JSON body:

{
"fromCurrency": "<From Currency ISO Code>",
"timestamp": "<Deal Timestamp in the format 'yyyy-MM-dd HH:mm:ss'>",
"toCurrency": "<To Currency ISO Code>",
"amount": <Deal Amount in Ordering Currency>
}

Replace `<From Currency ISO Code>`, `<Deal Timestamp>`, `<To Currency ISO Code>`, and `<Deal Amount>` with the appropriate values for your forex deal.

### 2. Get Forex Deal by ID - GET Method

Endpoint: `/rest/1.0/fxDeals/{dealId}`

This endpoint allows you to retrieve a forex deal by its ID. To use this endpoint, send a GET request with the `dealId` as a query parameter. For example: `/rest/1.0/fxDeals/12345`

Replace `12345` with the actual ID of the forex deal you want to retrieve.