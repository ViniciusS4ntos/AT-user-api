# Pegar o jdk 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY build/libs/user-api-0.0.1-SNAPSHOT.jar  /app/user-api-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/user-api-0.0.1-SNAPSHOT.jar"]