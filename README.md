# myRetail-api

myRetial api is a RESTful service that can retrieve/update product and price details by ID

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com/products/docker-desktop)
- [MongoDB](https://www.mongodb.com/download-center)

## Running the application locally

### Using Docker:

Prerequisites:

- Make sure docker is installed

Please follow steps below:
 - Execute `mvn clean install` from project root directory
 - Extract the file `./target/app.zip`
 - Navigate to extracted app directory
 - Run `docker-compose up`

### Using `main` method:
Prerequisites:
- Make sure mongodb is running locally on port 27017
- Alternativey you can use any existing mongodb instance by passing `-Dspring.data.mongdb.uri=-Dspring.data.mongodb.uri=mongodb://<hostname:port>/myRetailDB`

Please follow steps below:
- Execute the `main` method in the `com.myretail.casestudy.MyRetailApplication` class from your IDE

### Using maven spring-boot plugin:
Prerequisites:
- Make sure mongodb is running locally on port 27017

Please follow steps below:
- Execute `mvn spring-boot:run`

### Using terminal/shell:
Prerequisites:
- Make sure mongodb is running locally on port 27017
- Alternativey you can use any existing mongodb instance by passing `-Dspring.data.mongdb.uri=-Dspring.data.mongodb.uri=mongodb://<hostname:port>/myRetailDB`

Please follow steps below:
- Execute `mvn clean install` from project root directory
- Navigate to build directory `cd ./target/`
- Execute `java -jar myRetail-api.jar` for local mongo instance running on port 27017
- Execute `java -jar myRetail-api.jar -Dspring.data.mongdb.uri=-Dspring.data.mongodb.uri=mongodb://<hostname:port>/myRetailDB` for remote mongo instance

## Swagger Documentation
- [myRetail api documentation](http://localhost:8080/swagger-ui.html)

## Author
Madhavi Dintakurthy