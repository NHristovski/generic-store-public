FROM openjdk:11
ARG VERSION=latest
COPY target/product-${VERSION}-exec.jar /opt/app/product-${VERSION}.jar
EXPOSE 8200
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/product-${version}.jar
