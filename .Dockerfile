# syntax=docker/dockerfile:1

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve

COPY src ./src



CMD ["./mvnw", "spring-boot:run"]
