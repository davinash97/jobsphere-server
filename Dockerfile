FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/jobsphere-0.0.1-SNAPSHOT.jar app.jar
COPY .env .env

EXPOSE 6969

ENTRYPOINT ["java", "-jar", "app.jar"]