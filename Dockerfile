# Use the official OpenJDK as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/calculator-0.0.1-SNAPSHOT.jar app.jar

# Expose port 5000
EXPOSE 5000

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
