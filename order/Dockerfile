FROM openjdk:11
ARG VERSION=latest
COPY target/shipping-${VERSION}-exec.jar /opt/app/order-${VERSION}.jar
EXPOSE 8555
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/order-${version}.jar
