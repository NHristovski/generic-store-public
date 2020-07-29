FROM openjdk:11
ARG VERSION=latest
COPY target/user-${VERSION}-exec.jar /opt/app/user-${VERSION}.jar
EXPOSE 8080
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/user-${version}.jar
