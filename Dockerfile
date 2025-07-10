#FROM ubuntu:latest
#LABEL authors="ravip"
#
#ENTRYPOINT ["top", "-b"]

# Use official OpenJDK 21 runtime image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built jar file
COPY ./target/journalApp-0.0.1-SNAPSHOT.jar app.jar

# Expose port (change if your app uses a different one)
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]