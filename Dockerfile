# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and other necessary files first to take advantage of Docker cache for dependencies
COPY pom.xml .

# Download dependencies (without running the tests)
RUN mvn dependency:go-offline -B

# Copy the rest of the application code
COPY . .

# Package the application, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Create a smaller runtime image
FROM openjdk:17-jdk-slim

# Set the working directory for runtime
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/Job-Portal-0.0.1-SNAPSHOT.jar /app/Job-Portal.jar

# Expose port 8080 (default for Spring Boot applications)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "Job-Portal.jar"]
