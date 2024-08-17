# Stage 1: Build the Application

FROM maven:3.9.8 AS build

WORKDIR /app

COPY . .

RUN mvn clean package

# Stage 2: Create the runtime Image
FROM openjdk:21

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/jobsphere-0.0.1-SNAPSHOT.jar app.jar

# Copy the .env file
COPY --from=build /app/.env .env

EXPOSE 6969

ENTRYPOINT ["java", "-jar", "app.jar"]