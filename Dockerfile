FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY your-application.jar /app/your-application.jar

# Command to run the application when the container starts
CMD ["java", "-jar", "/app/your-application.jar"]