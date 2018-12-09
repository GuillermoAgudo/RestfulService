# Iinerate-service

This is a project of a RESTful itinerate conection application. This application is written in Java 1.8.
It is composed of two microservices:

| Service | Description |
| --- | --- |
| city-service | is the rest service of the application that contains the API. |
| filter-service | is the main rest service of the application. Here you consume the previous api in order get the best way to travel from one city to another.

# Requirements
 - Java 8
 - Apache Maven 3.5
 - docker 17

# Building

```
cd ./project_folder/
mvn clean package
```

This will generate a .jar file inside /target directory.


# Profiles of application

We can find two different type of profiles:

| Profile | Description |
| --- | --- |
| application.properties | for run the application with `java -jar` or `mvn spring-boot:run` |
| data.sql | data used to fill the database |

Note: if you want to use the docker profile, use this command:

```
mvn spring-boot:run -Dspring.profiles.active=docker
```

# Dockerizing

```
docker-compose up --build
```

Docker is another powerful tool for developing and delivering.

After running the commands above you can browse http://localhost:9090/ to see it working.

# Dependencies

### Spring Boot Framework
It's basically a suite, pre-configured, pre-sugared set of frameworks/technologies to reduce boiler plate configuration providing you the shortest way to have a Spring web application up and running with smallest line of code/configuration out-of-the-box. 
Used to create a simple RESTful application up and running with almost zero configuration. 
[Spring Boot Framework](http://projects.spring.io/spring-boot/)

### SpringFox
Automated JSON API documentation for API's built with Spring. Used for creating the itinerate-service documentation.
[SpringFox](https://github.com/springfox/springfox)

### Orika
Used for create the itinerate's mapper inside application.
[Orika](https://orika-mapper.github.io/orika-docs/index.html)

When the application started, you can browse http://localhost:9090/swagger-ui.html#/ to see it working.

### H2
Used to create a database in memory in order to avoid configurations of a normal database.
[H2](http://www.h2database.com/html/main.html)


