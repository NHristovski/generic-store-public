FROM openjdk:11
COPY target/shopping_cart-0.0.1-SNAPSHOT-exec.jar /opt/app/shopping_cart-0.0.1-SNAPSHOT.jar
EXPOSE 8777
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/opt/app/shopping_cart-0.0.1-SNAPSHOT.jar"]
