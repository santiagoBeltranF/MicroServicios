FROM eclipse-temurin:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} eureka_server.jar
ENTRYPOINT ["java", "-jar", "/eureka_server.jar"]