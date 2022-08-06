# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.6.0] - 06-08-2022

### Added
- New classes related to the changeless data

### Changes
- Added the explanations about patterns and decisions in the readme file
- Added new `url` configuration
- Updated test in `FirstPageCheckTest`
- Removal of Internet Explorer and Opera browser in `BrowserFactory`
- Updated the following libraries
  - `maven-surefire-plugin` to `3.0.0-M7`
  - `maven-compiler-plugin` to `3.10.1`
  - `junit.jupiter` to `5.9.0`
  - `assertj` to `3.23.1`
  - `selenium` to `4.3.0`
  - `webdrivermanager` to `5.2.3`
  - `log4j` to `2.18.0`
  - `allure` to `2.18.1`
  - `allure.junit5` to `2.18.1`

## [1.5.2] - 05-05-2022

### Changed
- Updated the following libraries
  - `allure.junit5` to `2.17.3`
  - `allure` to `2.17.3`
  - `assertj` to `3.22.0`
  - `junit.jupiter` to `5.8.2`
  - `selenium` to `4.1.4`
  - `webdrivermanager` to `5.1.1`
- Added browser name constants in the `FactoryTest`
- Fix assertions in the `ProductBuilderTest`
- Fix typo in `AmazonPage`

## [1.5.2] - 11-08-2021

### Changed
- Solving typo in `Department class`

## [1.5.1] - 11-08-2021

### Changed
- Added missing `@Test` annotation in the fluent interface test

## [1.5.0] - 11-08-2021

### Added
- Builder and Fluent interface examples

## [1.4.0] - 10-08-2021

### Added
- Owner configuration management library
- Test using the configuration file

### Changed
- Added possibility to execute headless tests using the `BrowserFactory` class

## [1.3.0] - 10-08-2021

### Added
- Allure report to automatically generate the test report

## [1.2.0] - 09-08-2021

### Added
- Log4J2 library and configuration file

### Changed
- Logging data generated in `CreditCardDataFactory`

## [1.1.0] - 09-08-2021

### Added
- Data Driven Test example

### Changed
- Moved `CreditCardDataFactory` to the correct package

## [1.0.0] - 09-08-2021

### Added
- Basic project structure
- Examples of the BaseTest Class, Factory (browser) and Data Factory
