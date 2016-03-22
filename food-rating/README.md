# Food Rating Web application

This web application allow user to select an authority from the drop down list and query the food rating profile for that locality.

## Requirements

1. Java 8
2. Maven or Gradle
3. Internet connection

## Technology stack

1. Java 8
2. Spring Boot MVC framework.
3. Thymeleaf templating framework for view.
4. Twitter Bootstrap.
5. Mockito

## Building and Running the application

1- Maven:

* Build: mvn clean package  (this will run the unit test and build the artifact)
* Run application: java -jar target/food-rating-0.1.0.jar
* Run Integration Test: mvn clean verify

2- Gradle: Gradle build task produces build/distributions folder which has zip or tar distribution of this application containing bash 
and bat scripts

* Build: gradle build  (this will run Unit, Integration test and build the artifact)
* Run: java -jar build/libs/food-rating-0.1.0.jar



## Test coverage

To produce test coverage for this application run the followings:
* Gradle: ./gradlew cleanTest test jacocoTestReport: this produces test coverage report in build/jacocoHtml
* Maven: the "mvn clean package" automatically produces test coverage report (cobertura) in target/site/cobertura








    