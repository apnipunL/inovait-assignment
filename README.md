# Inova IT (Bevolv) Assignment
Both Backend and Frontend projects are in this repository. Please read the instructions below before running the project

# Prerequisites to run the project (versions used in my local environment)
Tool / Framework | Version
--- | ---
Postgresql | 14.7
Java | 17
Spring Boot | 3.3.2
Angular | 16.2
Node JS | 18.16.0

# How to run the system locally
- Backend
    1. Clone the project, and open **leave-request-api** folder (I used Intellij Idea)
    2. Let it index
    3. Create an empty database (preferably named as: **inova-leave-request** (pgAdmin tool can be used to create the database)
    4. Change the **spring.datasource.url** value in application.properties if you used a different database name.
    5. Set **spring.datasource.username** and **spring.datasource.password** as per your local setup.
    6. Run the project (either using Intellij Idea Run button or use **mvn spring-boot:run** command in the terminal)
- Frontend
    1. Clone the project (if you haven't cloned already), open **leave-request-ui** folder (I used Intellij Idea)
    2. Run **npm install** command in terminal
    3. Run **ng serve** command in terminal
    4. Open **http://localhost:4200** and check the preview

# Note
Lombok is used in the application to reduce boilerplate code in the application. Make sure you have installed/enabled lombok in your IDE.
