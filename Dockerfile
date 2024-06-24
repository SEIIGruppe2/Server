FROM openjdk:17.0.10-jdk-alpine3.16

WORKDIR /app

COPY target/Server-1.0-SNAPSHOT.jar /app/Server-1.0-SNAPSHOT.jar

CMD ["java", "-jar", "/app/Server-1.0-SNAPSHOT.jar"]