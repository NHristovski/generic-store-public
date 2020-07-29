FROM openjdk:11
COPY target/inventory-0.0.1-SNAPSHOT-exec.jar /opt/app/inventory-0.0.1-SNAPSHOT.jar
EXPOSE 8599
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/opt/app/inventory-0.0.1-SNAPSHOT.jar"]
