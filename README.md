## Contents

- [Contents](#contents)
- [📖 Overview](#-overview)
- [✈️ Getting started](#️-getting-started)
  - [Prerequisites](#prerequisites)
  - [Running locally](#running-locally)
- [🔧 Techniques](#-techniques)
  - [Back End](#back-end)
  - [Front End](#front-end)
  - [Database](#database)
- [📄 Testing](#-testing)
- [🎨 Samples](#-samples)

## 📖 Overview

**_This project is an online food delivery solution._** Target users are clients who want to order food from phone application, and restaurants that want to achieve online delivery. It consists of two parts, including a mobile application, and a business management system.

## ✈️ Getting started

### Prerequisites

1.  [JAVA](https://www.oracle.com/java/technologies/downloads/) (version 1.8)
2.  [MySQL](https://dev.mysql.com/downloads/) (version 5.5 or higher)

### Running locally

1.  go to renaldo_eats/target, and open terminal here
2.  write 'java -jar renaldo_eats-0.0.1-SNAPSHOT.jar' in terminal to start the server
3.  open url 'http://localhost/back/page/login/login.html' to open the management system
4.  open url 'http://localhost/front/page/login.html' to open the mobile application, and switch website to mobile mode (Enter F12 and click 'Toggle device toolbar' if you use Chrome)

## 🔧 Techniques

### Back End

Used techniques include but not limit to JDK 1.8, Maven, Spring Boot, Spring Data JPA, Hibernate, Query DSL JPA, Spring MVC, RESTful API, Jackson, Druid, ThreadLocal, Slf4j, WebFilter, Session, Lombok, Commons-Collections4, MySQL-Connector-JAVA, JpaAuditing, Dynamic SQL, Stream, Java Mail Sender, Regular Expression, Encryption, Global Exception Handler, Custom Exception, BeanUtils, BigDaecimal, and Data Transfer Object.

### Front End

Use Vue and Element UI to write the front end code. Debugged by Chrome and Node.js. Modified code according to requirements and back-end code. Designed logo and icons by Photoshop.

### Database

MySQL

## 📄 Testing

**_Test-driven development._** All methods and functions were tested while developing. Junit was used for JAVA methods’ testing. SLF4J was used to show JAVA development logs. Debugged front-end code by Chrome and node.js. Implemented non-functional testing to improve user experience.

## 🎨 Samples

Management System Login Page

![](sample_images/Management%20System%20Login%20Page.png)

Management System Dish Display

![](sample_images/Management%20System%20Dish%20Display.png)

Management System Order Information Display

![](sample_images/Management%20System%20Order%20Information%20Display.png)

Mobile Application Login Page

![](sample_images/Mobile%20Application%20Login%20Page.png)

Mobile Application Ordering Page

![](sample_images/Mobile%20Application%20Ordering%20Page.png)

Mobile Application Add Address Page

![](sample_images/Mobile%20Application%20Add%20Address%20Page.png)

Mobile Application Order Successfully Page

![](sample_images/Mobile%20Application%20Order%20Successfully%20Page.png)

Mobile Application Order Detail Page

![](sample_images/Mobile%20Application%20Order%20Detail%20Page.jpg)

Mobile Application Settings Page

![](sample_images/Mobile%20Application%20Settings%20Page.png)
