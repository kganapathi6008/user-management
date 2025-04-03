# Use a multi-stage build to optimize the image size
FROM maven:3.8.5-openjdk-8 AS build

# Set working directory inside the container
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK image for running the app
FROM openjdk:8-alpine

# Set working directory
WORKDIR /opt/app

# Copy the built JAR from the first stage
COPY --from=build /app/target/spring-boot-mongo-1.0.jar spring-boot-mongo.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "spring-boot-mongo.jar"]

