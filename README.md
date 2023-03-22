# School Invoicing and Payments
This is an application for generating invoices and receiving payments for a school or any other institution.
## Features
* Enroll/Register Student
* Create/Manage Classes
* Manage Invoice Items
* Generate Invoices for Students in a Class
* Generate invoice for an individual Student
* Print Invoice as PDF
* Record Payments for a Student
* Send SMS notification for received Payments
* Generate Account Statement for a Student
* Different access levels

## Technologies
* Spring Boot 3.0
* Maven
* Angular 15
 
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+


To build and run the project, follow these steps:

* Clone the repository: `https://github.com/graccasoft/school-invoicing.git`
* Navigate to the project directory: `cd school-invoicing/backend`
* Build the project: `mvn clean install`
* Run the project: `mvn spring-boot:run`
* Run tests:  `mvn -Dtest=DispatchServiceImplTest test`
* Navigate to the project directory: `cd school-invoicing/frontend`
* Install frontend node packages: `npm install`
* Start the angular application for the frontend: `ng serve`

-> The backend application will be available at http://localhost:8081
-> The frontend application will be available at http://localhost:4200


# ER Diagram
![alt text](https://github.com/graccasoft/school-invoicing/blob/main/ER.drawio.png?raw=true)
