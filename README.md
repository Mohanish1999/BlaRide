# BlaRides
 ## About
**BLA-Rides** is a carpool booking platform that lets you to plan your commutes around area. Drivers and passengers can take advantage of our platform to offer or to find a daily commute as well as a long distance travel.

The application was based on **Spring Framework** and server-side Java template engine **Thymeleaf**. User registration and login page were secured by CSRF protection using **Spring Security**. User can manage booking reference through booking history page or notification service. The booking service was supported by **Google API**, **iCalendar integration** and **Spring Boot Mail**. The booking reference listing page implemented **PagingAndSortingRepository** and **Search Functionality**.

### Technologies
 - Spring Framework: Spring Boot, Spring MVC, Spring Data, Spring Security, and Spring Test
 - Google API: Google Map, Google Direction, Google Distance Matrix and Google Autocomplete
 - Docker
 - Hibernate
 - MySQL Database
 - Thymeleaf Template Engine
 - Twitter Bootstrap
 - jQuery, HTML, CSS, JavaScript
 - JS Plugins: geobytes, datetimepicker, datatable, slider and etc.

## Setup

### System requirements

 - Java 7 or later
 - Maven 3.2+
 - MySQL 5.6 or better
 - You can also import the code straight into your IDE:
   - Spring Tool Suite (STS)
   - IntelliJ IDEA
   - Eclipse
   
### Create database

    $ mysql> create database blarides;
    $ mysql> create user 'root'@'localhost' identified by 'passw0rd!';
    $ mysql> grant all on blarides.* to 'root'@'localhost';
    
    # If you are running the application for the first time, after Hibernate created the tables from entities
    # Execute the following script:
    $ INSERT INTO role (role_id, name) VALUES ('1', 'ROLE_USER');
    $ INSERT INTO role (role_id, name) VALUES ('2', 'ROLE_ADMIN');



### Directory Structure
```shell
└── src
    └── main
        └── java
            └── config
            └── controller
            └── domain
            └── error
            └── repository
            └── service
            └── util
            └── CarpoolApplication.java
        └── resources
            └── static
            └── templates
            └── application.properties
     └── test
├── pom.xml
└── .gitignore

```

## Building the Project
    # Run the application
    $ mvn spring-boot:run
    
    # You can also run as a packaged application
    $ mvn clean install
    $ java -jar target/demo-0.0.1-SNAPSHOT.jar

    # You might also want to use the MAVEN_OPTS operating system environment variable:
    $ export MAVEN_OPTS=-Xmx1024m

