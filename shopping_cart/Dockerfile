FROM openjdk:11
ARG VERSION=latest
COPY target/shopping_cart-${VERSION}-exec.jar /opt/app/shopping_cart-${VERSION}.jar
EXPOSE 8777
ENV version=$VERSION
ENTRYPOINT java -jar -Xms256m -Xmx2048m /opt/app/shopping_cart-${version}.jar
