# Drone Management - Musala
### Technology Stack
- Java 11
- Spring Boot
- H2 DB: In-memory DB.
- Docker

### Description of service
The service are set of REST API endpoints that allows for the user to 
- register a drone
- load a drone with medication items
- check loaded medication items for a given drone
- check available drones for loading: ***A drone is available when it is in the state of IDLE or LOADED, but with space to accommodate more medication items.***
- check drone battery level for a given drone;
- register a medication
- Get all registered medications
- Get all registered drones
- periodically (30 minutes interval) check and log the battery levels for each of the registered drones.

### Constraints and Assumptions
- Drones are prevented from being loaded with medications with more weight that they can carry; 
- Drones are prevented from being in LOADING state if the battery level is below 25%;
- Only registered medications can be loaded
- Medication names must follow a certain pattern (it can only contain numbers, letters, _, -)
- Each medication must have a unique code at the point of registering

### Starting Application
The application has been packaged in a docker container. 
Navigate to the root of the application and start the application with the following commands
```
mvn clean package
docker-compose up
```

The application will start on port 5000.
### Testing
Once the application starts, navigate to 
[http://localhost:5000/swagger-ui](http://localhost:5000/swagger-ui.html) on a web-browser. 
The available endpoints can be tested from the loaded Swagger Interface



