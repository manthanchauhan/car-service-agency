# Pre-requisites
1. Java 17
2. MySQL 8.3.0

# How to run
## Setting up the database
1. Create database & user.
    ```sql
    CREATE DATABASE car_service_agency;
    CREATE USER car_service_agency IDENTIFIED BY 'car_service_agency@123';
    GRANT ALL PRIVILEGES ON car_service_agency.* TO car_service_agency;
    ```
2. Download the migrations file `src/main/resources/car_service_agency.sql`.
3. Run the migrations file.
    ```bash
    mysql -u car_service_agency -p car_service_agency < car_service_agency.sql
    ```

## Running the application
1. Install the JAR file `target/car_service_agency_new-0.0.1-SNAPSHOT.jar`.
2. Run the JAR file.
    ```bash
    java -jar car_service_agency_new-0.0.1-SNAPSHOT.jar
    ```
3. The application will be running on `http://localhost:8080`.
4. Find the postman collection in `src/main/resources/car_service_agency.postman_collection.json`.