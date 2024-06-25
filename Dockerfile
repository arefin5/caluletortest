# Use OpenJDK 17 JDK Alpine as base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/calculator-0.0.1-SNAPSHOT.jar app.jar

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
