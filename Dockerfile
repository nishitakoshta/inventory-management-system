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
RUN ./gradlew clean build -x test

# Create necessary log directories if you’re writing logs to a specific path
RUN mkdir -p /home/inventory-logs

# Expose the port your Spring Boot app will run on
EXPOSE 8085

# Command to wait for MySQL to be ready and then start the application
CMD ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "build/libs/inventorymanagementsystem-0.0.1-SNAPSHOT.jar"]
