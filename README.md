# MyRetail-api
This is a Spring Boot RESTful service that can retrieve product and its price details.

## Requirements
For building and running the application you need:

- [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [Maven 3](https://maven.apache.org)
- Docker 1.13.1
- Docker-compose 1.21.0

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.myretail.casestudy.MyRetailApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Build and Run
Will build, package and create a new Docker image with the application

```mvn clean package```

Launch an Application and a Mongo container

```docker-compose up```

## Docker
The docker file defines a container based on Java with the jar created by maven package, the `dockerfile-maven-plugin` is responsible to build a new image, a push could be configured in the pom file also.

## Docker-compose
The Docker-compose file describes our multi-container application, the application consists in 2 containers, one running the MyRetail-api and a linked container with MongoDB. These containers have the 8080 port exposed for the RestAPI and 27017 for mongo.

## Swagger Documentation
- [myRetail api documentation](http://localhost:8080/swagger-ui.html)

## Author
Madhavi Dintakurthy