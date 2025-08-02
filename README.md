# 3 Design Patterns and Architectural Decisions for your Test Project

Remember to give this project a ⭐

This repo is part of a presentation with the same name. You can find the following resources:

*
Slides: https://speakerdeck.com/eliasnogueira/3-design-patterns-and-architecture-decisions-you-must-use-in-your-project
* Video: https://youtu.be/kqwPHdGn7sI?t=22438

## TOC

* [Patterns](#patterns)
    * [Base Test class](#base-test-class)
    * [Builder and Fluent Interface](#builder-and-fluent-interface)
        * [Builder](#builder)
        * [Fluent Interface](#fluent-interface)
    * [Factory](#factory)
* [Architectural Decisions](#architectural-decisions)
    * [Data Generation](#data-generation)
        * [Fake data generation](#fake-data-generation)
        * [Static data generation](#static-data-generation)
        * [Changeless data](#changeless-data)
        * [Data driven](https://github.com/eliasnogueira/3-design-patters-arch-decisions/tree/update-and-changelesss-data#data-driven)
    * [Configuration Management](#configuration-management)
    * [Log and Reports](#log-and-reports)
        * [Exception and basic logs](#exception-and-basic-logs)
        * [Test Reports](#test-reports)

## Patterns

### Base Test class

It is a simple approach to set up a shared initialization and cleanup in your tests. We commonly categorize it as a Testing pattern.

The problem we are trying to solve here is the reuse of common behaviors across test classes, avoiding coding duplication, and centralizing these actions at one point.

The application of a Base test class in OO programming languages is applied using inheritance.

Without this approach, we have code duplicated related to:

* pre-condition
* post-condition
* any shared method

With this approach, we have:

* the smart use of inheritance
* an easy way to add more tests
* flexible creation of test suites

#### Examples

##### Without the usage of the Base Test class

* [FirstPageCheckTest](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/java/com/eliasnogueira/basetest/simple/without/FirstPageCheckTest.java)

##### With the usage of the Base Test class

* [BaseTestWeb](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/java/com/eliasnogueira/basetest/simple/with/BaseTestWeb.java)
* [FirstPageCheckTest](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/java/com/eliasnogueira/basetest/simple/with/FirstPageCheckTest.java)

### Builder and Fluent Interface

#### Builder

This pattern tries to manage the construction process of an object using its constructor in fluent methods.

Without the Builder pattern, we create objects like:

```java
class BuilderExample {

    @Test
    void example() {
        // approach using methods
        Product product = new Product();
        product.setName("iPod");
        product.setDescription("10000 songs in your pocket!");
        product.setPrice(new BigDecimal("123.99"));

        // approach using constructor (not so legible)
        Product product = new Product("iPod", "10000 songs in your pocket!", new BigDecimal("123.99"));
    }
}
```

The Builder creation consists of a new class with the same fields, but with the following changes:

* set methods will return the builder class
* a `build` method consisting of the main object creation and all the possible restrictions you might add.

The usage of the Builder pattern will bring clarity to the code:

```java
class BuilderExample {

    @Test
    void example() {
        Product product = new ProductBuilder()
                .name("iPod")
                .description("10000 songs in your pocket!")
                .price(new BigDecimal("123.99"))
                .build();
    }
}
```

##### Examples

* [Product](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/builder/pattern/Product.java)
* [ProductBuilder](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/builder/pattern/ProductBuilder.java)
* [ProductBuilderTest](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/java/com/eliasnogueira/builder/ProductBuilderTest.java)

#### Fluent Interface

This pattern is based on Builder, where we try to provide a readable Fluent API over a specific domain. The domain can include more than one class.
This might not have the `build()` method to create an object.

```java
class AmazonPage {

    public AmazonPage selectDepartment(Department department) {
        // some magic here
        return this;
    }

    public AmazonPage search(String term) {
        // some magic here
        return this;
    }
}
```

##### Examples

* [AmazonPage](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/builder/fluentinterface/AmazonPage.java)
* [Department](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/builder/fluentinterface/Departament.java)
* [FluentInterfaceTest](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/java/com/eliasnogueira/fluentinterface/FluentInterfaceTest.java)

### Factory

This creational pattern enables the creation of objects without exposing the internal logic.
A good example of this is the creation of different browser instances.

The basic implementation is done by having specific classes that will create different objects, while the main class is responsible for understanding the object type to create it.

#### Examples

* [BrowserFactory](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/factory/browser/BrowserFactory.java)
* [FactoryTest](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/java/com/eliasnogueira/factory/browser/FactoryTest.java)

## Architectural Decisions

### Data Generation

There are many ways to generate data. The most common are:

* fakes
* static and dynamic generation

#### Fake data generation

Create an approach to generate non-sensitive data for your test without manually changing the test data in each execution.
There are a lot of tools to create this type of data.

Example with [java-faker](https://github.com/DiUS/java-faker)

```java
class JavaFakerExample {

    void example() {
        Faker faker = new Faker("pt-BR");
        faker.name().fullName();
        faker.address().fullAddress();
        faker.internet().emailAdress();
        faker.business().creditCardNumber();
        faker.date().birthday();
    }
}
```

The best way to get the advantage of the fake data generation is by combining it with the Factory pattern, where we commonly refer to it as the Data Factory.

We can use a Fake Generation in a centralized data class that can create data in any condition.

```java
class CreditCardDataFactory {

    public CreditCard validCreditCard() {
    }

    public CreditCard invalidCreditCardNumber() {
    }

    public CreditCard invalidCvv() {
    }

}
```

##### Examples

* [CreditCardDataFactory](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/data/factory/CreditCardDataFactory.java)
* [DataFactoryTest](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/java/com/eliasnogueira/data/factory/DataFactoryTest.java)

#### Static data generation

When the data causes different behaviors in your application.

A Static approach can be achieved by implementing any data approach, like:

* Class
* CSV | JSON | TXT | YML
* Database
* Mock

#### Changeless data

It’s a set of common data used across the project, consisting of a final class containing constants.
The changeless data (constants) will be used in different classes, and the advantage is a single point of change.
We can have multiple classes shaping the different data requirements.

##### Examples

* [AssertionData](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/data/changeless/AssertionData.java)
* [BrowserData](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/data/changeless/BrowserData.java)
* [GeneralDatta](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/data/changeless/GeneralData.java)

#### Data-driven

It is one of the Static data generation types.
It consists of using different data in the same test, where the data changes, not the test.

Different test libraries have this approach available.
Most of them consist of the data hard-coded in a test class.

##### Example

* [CreditCardDataDriven](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/data/driven/CreditCardDataDriven.java)
* [DataDrivenTest](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/java/com/eliasnogueira/data/driven/DataDrivenTest.java)

#### Dynamic data generation

The Dynamic approach can be implemented according to your context.
Used to remove the maintenance of test data. Example:

* Queries in a database
* Consume data from an API

### Configuration Management

It provides a way to set different values for a running application without the necessity of re-compiling or re-running it.
Normally, this approach accepts dynamic values injection through environment variables or by modifying a configuration
file.

#### Examples

* [Configuration](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/config/Configuration.java)
* [ConfigurationManager](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/config/ConfigurationManager.java)
* [config.properties](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/resources/config.properties)
* [WebConfigTest](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/java/com/eliasnogueira/configuration/WebConfigTest.java)

### Log and Reports

We can generate logs and reports in different ways, but it’s important to have both in your test project, whatever tools you choose for this.

#### Exception and basic logs

By using any log strategy, saving a log file, we can understand the common errors that occurred during the test
execution.

These errors can be of:

* assertion errors
* timeout exceptions
* locator exception
* an exception to your architecture

You can also log basic info to know some action you are doing in the code.

##### Examples

* [log4j2.properties](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/resources/log4j2.properties)
* [CreditCardDataFactory](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/main/java/com/eliasnogueira/data/factory/CreditCardDataFactory.java)
* `3-design-patterns-arch-decision.log` file generated in your user folder

#### Test Reports

It does not matter if you will generate the test report in any style (below): the most important thing is to have one to satisfy your requirements.

We can have it reported as:

* Gherkin style (readable by humans)
* xUnit style (readable by CI/CD tools)
* HTML (readable by humans)
* any other style like XML, JSON, etc…

##### Examples

* [allure.properties](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/src/test/resources/allure.properties)
* Allure configuration placed
  on [pom.xml](https://github.com/eliasnogueira/3-design-patters-arch-decisions/blob/main/pom.xml)
* auto-generated xUnit XML reports at `target/surefire-reports`
