FROM openjdk:11
ARG VERSION=latest
COPY target/service_registry-${VERSION}-exec.jar /opt/app/service_registry-${VERSION}.jar
COPY application-docker.properties /opt/app/application.properties
EXPOSE 8761
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/service_registry-${version}.jar --spring.config.location=file:/opt/app/application.properties




