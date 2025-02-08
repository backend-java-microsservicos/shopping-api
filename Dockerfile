FROM eclipse-temurin:21-alpine-3.21
VOLUME /tmp
ARG JAR_FILE=target/shopping-api.jar
COPY $JAR_FILE app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]