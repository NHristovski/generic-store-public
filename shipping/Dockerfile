FROM openjdk:11
COPY target/shipping-0.0.1-SNAPSHOT-exec.jar /opt/app/shipping-0.0.1-SNAPSHOT.jar
EXPOSE 8555
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/opt/app/shipping-0.0.1-SNAPSHOT.jar"]
