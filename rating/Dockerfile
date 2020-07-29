FROM openjdk:11
ARG VERSION=latest
COPY target/rating-${VERSION}-exec.jar /opt/app/rating-${VERSION}.jar
EXPOSE 8205
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/rating-${version}.jar
