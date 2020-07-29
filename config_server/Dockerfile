FROM openjdk:11
COPY target/config_server-0.0.1-SNAPSHOT-exec.jar /opt/app/config_server-0.0.1-SNAPSHOT.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/opt/app/config_server-0.0.1-SNAPSHOT.jar"]
