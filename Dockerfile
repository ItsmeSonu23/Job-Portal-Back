FROM maven:3.8.5-openjdk-23 AS builder

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:23-jdk-slim

COPY --from=builder /target/Job-Portal-0.0.1-SNAPSHOT.jar Job-Portal.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","Job-Portal.jar"]
