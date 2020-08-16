FROM openjdk:11
ARG VERSION=latest
COPY target/config_server-${VERSION}-exec.jar /opt/app/config_server-${VERSION}.jar
COPY application-docker.properties /opt/app/application.properties
EXPOSE 8888
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/config_server-${version}.jar --spring.config.location=file:/opt/app/application.properties


