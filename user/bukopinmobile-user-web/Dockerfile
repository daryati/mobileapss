FROM openjdk:8-jre-alpine
LABEL maintainer="Stephen Dharma <stephen.dharma@asyst.co.id>"

VOLUME /tmp
COPY target/*.jar app.jar

EXPOSE 80 8080

ENTRYPOINT ["java","-jar","/app.jar"]
