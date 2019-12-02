FROM openjdk:11.0.5-jdk

VOLUME /tmp
ADD myRetail-api.jar /opt/MyRetail/
EXPOSE 8080
WORKDIR /opt/MyRetail/
CMD ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/myRetailDB", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "myRetail-api.jar"]
