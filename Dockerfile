FROM openjdk:17-alpine

ARG JAR_FILE=target/grocery-list-1.0.0.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]
