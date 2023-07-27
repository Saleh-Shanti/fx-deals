# FX Deals Data Warehouse

This project is a data warehouse for Bloomberg, designed to analyze FX deals. 
It allows users to submit FX deal details and persist them into a PostgreSQL database. 
The project is built using Quarkus, a Java framework for building cloud-native applications.

## Features

- Accepts FX deal details as a request with fields: Deal uniqueId, From Currency ISO Code, To Currency ISO Code, Deal timestamp, Deal amount in ordering currency.
- Validates the row structure of the request.
- Ensures that the same request is not imported twice.
- Persists the valid FX deal requests into the PostgreSQL database.
- Provides a workable deployment with Docker Compose for easy setup.

## Prerequisites

Before running the project, ensure you have the following installed on your system:

- Java Development Kit (JDK) 11 or higher
- Maven 3.8.* or higher
- Docker (with Docker Compose support) optional
- PostgreSQL database

## Getting Started

1. Clone the repository to your local machine.
2. Set up the PostgreSQL database with the required schema and credentials.
3. Configure the database connection in the `application.properties` file.
4. Build the project using Maven:

```bash
mvn clean package

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

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

## Creating a native executable

You can create a native executable using:
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/fx-deals-1.0.0-SNAPSHOT-runner`
