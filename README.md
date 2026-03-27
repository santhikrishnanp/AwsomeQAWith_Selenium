# AwesomeQA Selenium Automation

[![Build Status](http://localhost:8080/job/Selenium_AwsomeQATests/badge/icon)](http://localhost:8080/job/Selenium_AwsomeQATests/)

![Selenium Tests](https://github.com/santhikrishnanp/AwsomeQAWith_Selenium.git/actions/workflows/tests.yml/badge.svg)

# # Selenium UI Automation Framework (Page Object Model)

A modular, maintainable UI automation framework built using **Selenium WebDriver**, **TestNG**, and the **Page Object Model (POM)** design pattern.  
This project is structured for scalability, CI/CD execution, and clean separation of concerns.

---

## 🚀 Tech Stack

- **Java 21**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **WebDriverManager**
- **GitHub Actions (CI/CD)**

---

## 📁 Project Structure


---

## 🧪 Running Tests

### Run full regression suite


[//]: # (mvn clean test -DsuiteXmlFile=testSuites/testng-regression.xml)

###  (Run in headless mode)

###  (mvn clean test -Dbrowser=chrome-headless -DsuiteXmlFile=testSuites/testng-regression.xml)

[//]: # (Browser Configuratio)
[//]: # (src/main/resources/config-awsomeqa.properties)

[//]: # ()
[//]: # (or via Maven)

[//]: # (-Dbrowser=chrome)

[//]: # (-Dbrowser=chrome-headless)

[//]: # ()
[//]: # (Headless mode uses)

[//]: # (--headless=new)

[//]: # (--window-size=1920,3000)