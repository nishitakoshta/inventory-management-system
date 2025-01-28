# Use an official OpenJDK image as a base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle wrapper and Gradle files first
COPY gradlew /app/gradlew
COPY gradle /app/gradle
RUN chmod +x /app/gradlew

# Copy the rest of your project files into the container
COPY . /app

# Install Gradle dependencies and build the project
RUN ./gradlew clean build -x test

# Expose port 8085 (or whichever port your app is using)
EXPOSE 8085

# Command to run the application (adjust according to your project setup)
CMD ["java", "-jar", "build/libs/inventorymanagementsystem-0.0.1-SNAPSHOT.jar"]
