FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/StudentTestTask-0.0.1.jar
EXPOSE 8090
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


