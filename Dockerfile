#FROM openjdk:17-jre-slim
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the application JAR file into the container
#COPY target/VTC-0.0.1-SNAPSHOT.jar .
#
## Command to run the application when the container starts
#CMD ["java", "-jar", "VTC-0.0.1-SNAPSHOT.jar"]


#
# Build stage
#
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]