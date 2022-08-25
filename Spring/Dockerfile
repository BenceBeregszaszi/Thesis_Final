FROM maven:3.8.5-openjdk-18 as build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:18.0
COPY --from=build /usr/src/app/target/*.jar /usr/app/backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/app/backend.jar"]