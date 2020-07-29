FROM openjdk:11
ARG VERSION=latest
COPY target/auth-${VERSION}-exec.jar /opt/app/auth-${VERSION}.jar
EXPOSE 9100
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/auth-$version.jar
