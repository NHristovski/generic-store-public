FROM openjdk:11
COPY target/service_registry-0.0.1-SNAPSHOT-exec.jar /usr/app/service_registry-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/usr/app/service_registry-0.0.1-SNAPSHOT.jar"]
