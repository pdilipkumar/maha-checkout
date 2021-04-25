# maha-checkout

### Project setup steps
* Clone the github [repo](https://github.com/pdilipkumar/maha-checkout) to local
* Navingate to **maha-checkout** root directory
* Execute command `./gradlew clean build` in Git Bash to build the project which also executes unit tests.
* Execute command `./gradlew integrationTest` in Git Bash to run integration tests
* To start the application execute command `./gradlew bootRun` or `java -jar build/libs/maha-checkout-*.jar` in Git Bash

    ####Note:
    * For execution in Windows Command Prompt replace `./gradlew` with `gradlew`. 
    * Append `--info` in gradle commands for detailed logs.
    * To view in-memory database content after application startup navigate to [h2-console](http://localhost:8080/h2-console) in web browser.

### My Approach
My first thought was to follow de-facto standard of using project structure like controller -> service -> repository layers. 
To communicate between different layers I used POJOs, Resource for controller layer, DTO(although not used in current project 
because of simple integer value to work with) for service layer & Entity for persistent layer and use assemblers to transform 
or copy data to different POJOs. I chose in-memory database(h2) instead of just using objects or loading data from file for extensibility purpose 
where more products or discounts for a product can be added at runtime. This also helps in using SQL for some business logic like getting discount for a product in this scenario. 
Also spring has very good support for embedded DB(or any other database in general) and can easily be replaced by other DB implementations like Oracle or MySql.