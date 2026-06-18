# Use a base image with JDK 25
FROM openjdk:25-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
# Assuming the JAR is built and located in target/
COPY target/ping-service-0.1.4-SNAPSHOT.jar app.jar

# Expose the port the Spring Boot application runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
