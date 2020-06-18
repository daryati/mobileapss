FROM openjdk:8-jre-alpine
LABEL maintainer="Stephen Dharma <stephen.dharma@asyst.co.id>"

USER root
# Copy Centagate certs to container's JRE
COPY ./jssecacerts $JAVA_HOME/lib/security

# COPY centagate-ip.crt $JAVA_HOME/lib/security
#RUN \
#    cd $JAVA_HOME/lib/security \
#    && keytool -keystore cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias ldapcert -file centagate-ip.crt

VOLUME /tmp
COPY target/*.jar app.jar

EXPOSE 80 8080

ENTRYPOINT ["java","-jar","/app.jar"]
