FROM adoptopenjdk/openjdk11:alpine-slim
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
COPY config/theconfig.json config.json
ENTRYPOINT ["java","-jar","app.jar"]