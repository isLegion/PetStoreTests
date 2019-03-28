# PetStoreTests

Framework for testing of RESTful API of online pet stores : https://petstore.swagger.io

### Language: Java

Using tools and libraries:
1. TestNg - test tunner
2. Rest Assured - framework for testing of REST services https://github.com/rest-assured/rest-assured
3. Lombok - library for easily building Java bytecode into my Pet object (Please install lombock plugin)
4. Allure - tool for reports creating (https://github.com/allure-framework/allure-java/tree/master/allure-rest-assured)
5. Assertj - library which provides different types of assertions for tests (http://joel-costigliola.github.io/assertj/assertj-core.html)

### Project contains 4 Test classes:

- AddNewPetTest.java
- CheckDeletingPetTest.java
- CheckExistingPetTest.java
- CheckUploadImagePetTest.java

### All steps used in tests are in class ServicesPetSteps.java. 

Configuration file : /../petstoreEPAM/src/main/resources/config.properties

Object for tests : /../petstoreEPAM/src/main/java/test/data/Pet.java

### For run test use these command:

- `mvn clean test`

When the tests pass

- `allure serve build/allure-results`




