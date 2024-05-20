# Library-Management-System

## Installation

### Database and Tables Setup

1. Clone this repository or download it as a zip.
2. Run MySQL as the root user and create a database named `library_system_db` with the username "library_system" and password "library_system".
3. Log in to MySQL with the created username and password.
4. Find the `tables.sql` file in the `sql` folder of this repository and run it to create tables in your database.

### Running the Application

1. Run the application as a Java application using the following command:
   ```sh
   mvn clean compile
   mvn clean install
   java -jar target/librarysystem-0.0.1-SNAPSHOT.jar
### API Docs
You will find the API documentation for this application in the api-docs folder in JSON and YAML format.
API documentation [Library Management System](https://documenter.getpostman.com/view/31701906/2sA3QmEasP)

### Register and Login Service
All the APIs of this application require authentication. Therefore, you must first register yourself as a user or staff member using the "/auth/register" API [click here](https://documenter.getpostman.com/view/31701906/2sA3QmEasP#2f00eec4-2ada-4b2f-ab65-dc20656bfaee). If you are already registered, you can proceed by logging in using the "/auth/login" API [click here](https://documenter.getpostman.com/view/31701906/2sA3QmEasP#82636bfa-cdb4-4c7b-be78-980fef98ab04). This application utilizes bearer token authentication, so the login service will provide a token in response. This token can be used to access other APIs based on your role.
