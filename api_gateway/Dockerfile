FROM openjdk:11
COPY target/api_gateway-0.0.1-SNAPSHOT-exec.jar /opt/app/api_gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8762
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/opt/app/api_gateway-0.0.1-SNAPSHOT.jar"]
