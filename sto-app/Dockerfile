FROM eclipse-temurin:21-jdk-alpine
LABEL authors="baurzhanov"

ARG JAR_FILE=target/sto-app-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
