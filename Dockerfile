FROM maven:3.6-jdk-11 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

FROM adoptopenjdk/openjdk11:alpine-slim
COPY --from=builder /app/target/*.jar /app/DevOpsExam_10021-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","app/DevOpsExam_10021-0.0.1-SNAPSHOT.jar"]