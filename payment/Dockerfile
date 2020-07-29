FROM openjdk:11
COPY target/payment-0.0.1-SNAPSHOT-exec.jar /opt/app/payment-0.0.1-SNAPSHOT.jar
EXPOSE 18080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/opt/app/payment-0.0.1-SNAPSHOT.jar"]
