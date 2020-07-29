FROM openjdk:11
COPY target/product-0.0.1-SNAPSHOT-exec.jar /opt/app/product-0.0.1-SNAPSHOT.jar
EXPOSE 8200
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/opt/app/product-0.0.1-SNAPSHOT.jar"]
