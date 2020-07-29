FROM openjdk:11
ARG VERSION=latest
COPY target/inventory-${VERSION}-exec.jar /opt/app/inventory-${VERSION}.jar
EXPOSE 8599
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/inventory-${version}.jar
