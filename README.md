# Used Technologies

---

## Database
```
  db:
    image: 'mysql:8'
    volumes:
      - './src/main/resources/initDb.sql:/docker-entrypoint-initdb.d/initDb.sql'
    ports:
      - '127.0.0.1:3306:3306'
```
 The database it containerized with docker which is using Mysql image.
 When the application stats up it is automatically create and fill the database.
 After the initialization it is exposed on localhost with basic database port.
 
 **NOTE: If your system running database in the background you have to
 shut id down or the application can not start!<br>
 In Ubuntu:**
 ```
 sudo service mysql stop
 ```

---

## Backend

```
FROM maven:3.8.5-openjdk-18 as build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:18.0
COPY --from=build /usr/src/app/target/*.jar /usr/app/backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/app/backend.jar"]
```

 The backend is a spring boot application which is based on java technologies.
 The server is running in a separate container.
 First the container recompile then the server exposed on port 8080.
 
 It can accept requests and give answers as responses. **(JSON)**

### Layers:

#### Controller:
 **DTO** is stand for Data Transfer Object which is made for the communication for inside and outside.

 The requests are accepted by the controller. **(with correct authority)**
 After the request sent down to the **Service Layer**


#### Service:
 The service Layer makes conversions from DTO to Entity 
 and makes the business logic. 

#### DAO:
 **DAO** is stands for Data Access Object. 
 These DAOs (Entities) are representations of the database tables structure.
 The DAOs second part is the Repositories. The repositories are contains functions that represents SQL commands.


### Testing:

The tests are running when the 
``` 
docker-compose up --build
```
command is executed. 
The tests are running in an in-memory database **(H2)** for faster test runs.

---

## Mobil

---

## Useful commands:
```
 - docker-compose down  //drop down containers
 - docker-compose up  //starts containers (not the latest compiled)
 - docker-compose build  //builds containers
```