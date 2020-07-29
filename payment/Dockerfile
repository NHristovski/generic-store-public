FROM openjdk:11
ARG VERSION=latest
COPY target/payment-${VERSION}-exec.jar /opt/app/payment-${VERSION}.jar
EXPOSE 18080
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/payment-${version}.jar


