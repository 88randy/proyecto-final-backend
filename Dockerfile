#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean install

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build /target/proyecto-final-backend-0.0.1-SNAPSHOT.jar proyecto-final-backend.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","proyecto-final-backend.jar"]