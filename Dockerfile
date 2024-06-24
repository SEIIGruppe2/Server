FROM openjdk:17-alpine

WORKDIR /app

COPY target/Server-1.0-SNAPSHOT.jar /app/Server-1.0-SNAPSHOT.jar

CMD ["java", "-jar", "/app/Server-1.0-SNAPSHOT.jar"]