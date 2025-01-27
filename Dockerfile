# OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from your local machine to the container
COPY build/libs/karmunity-backend-0.0.1-SNAPSHOT.jar karmunity-backend.jar

# Expose port 8080
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "karmunity-backend.jar"]