# Start of Selection
# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17 AS builder
# Copy the rest of the application code
COPY . .

# Package the application, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Create a smaller runtime image
FROM openjdk:17-slim

# Copy the built JAR file from the builder stage
COPY --from=builder /target/Job-Portal-0.0.1-SNAPSHOT.jar Job-Portal.jar

# Expose port 8080 (default for Spring Boot applications)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "Job-Portal.jar"]
# End of Selection
