# Use an official OpenJDK image as a base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle wrapper and Gradle files first
COPY gradlew /app/gradlew
COPY gradle /app/gradle
# Copy the rest of your project files into the container
COPY . /app

# Give executable permissions to gradlew (if not already executable)
RUN chmod +x /app/gradlew

# Install Gradle dependencies and build the project
RUN ./gradlew clean build

# Create necessary log directories if you’re writing logs to a specific path
RUN mkdir -p /home/inventory-logs

# Expose port 8085 (or whichever port your app is using)
EXPOSE 8085

# Command to run the application (adjust according to your project setup)
CMD ["java", "-jar", "build/libs/inventorymanagementsystem-0.0.1-SNAPSHOT.jar"]
