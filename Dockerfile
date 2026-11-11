# Stage 1: Build application using Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B || true

# Copy source and build jar
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: Minimal JRE 21 Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Ensure uploads directory exists
RUN mkdir -p uploads

# Copy executable jar from builder stage
COPY --from=build /app/target/*.jar app.jar

# Render passes dynamic port in PORT env variable (default to 8080)
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar", "--spring.profiles.active=render"]
