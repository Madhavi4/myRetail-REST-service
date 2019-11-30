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
#### Prerequisites:
    - Make sure docker is installed

    Please follow steps below:
    - ```mvn clean install```
    - Exract the file `cd ./target/app.zip`
    - Navigate to extracted app directory
    - Run `docker-compose up`

### Using `main` method:
#### Prerequisites:
    - Make sure mongodb is running locally on port 27017
    - Alternativey you can use any existing mongodb instance by passing `-Dspring.data.mongdb.uri=-Dspring.data.mongodb.uri=mongodb://<hostname:port>/myRetailDB`

    Execute the `main` method in the `com.myretail.casestudy.MyRetailApplication` class from your IDE

### Using maven spring-boot plugin:
#### Prerequisites:
    - Make sure mongodb is running locally on port 27017

    Execute `mvn spring-boot:run`

### Using terminal/shell:
#### Prerequisites:
    - Make sure mongodb is running locally on port 27017
    - Alternativey you can use any existing mongodb instance by passing `-Dspring.data.mongdb.uri=-Dspring.data.mongodb.uri=mongodb://<hostname:port>/myRetailDB`

    Please follow steps below:
    - ```mvn clean install```
    - Navigate to build directory `cd ./target/`
    - For local mongo instance running on port 27017 `java -jar myRetail-api.jar`
    - For remote mongo instance `java -jar myRetail-api.jar -Dspring.data.mongdb.uri=-Dspring.data.mongodb.uri=mongodb://<hostname:port>/myRetailDB`

## Swagger Documentation
- [myRetail api documentation](http://localhost:8080/swagger-ui.html)

## Author
Madhavi Dintakurthy