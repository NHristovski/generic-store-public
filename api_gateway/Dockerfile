FROM openjdk:11
ARG VERSION=latest
COPY target/api_gateway-${VERSION}-exec.jar /opt/app/api_gateway-${VERSION}.jar
EXPOSE 8762
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/api_gateway-${version}.jar
