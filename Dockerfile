FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

EXPOSE 8080

ARG JAR_FILE=target/Quasar-1.0.0.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]