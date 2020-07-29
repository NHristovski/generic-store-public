FROM openjdk:11
COPY target/user-0.0.1-SNAPSHOT-exec.jar /opt/app/user-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/opt/app/user-0.0.1-SNAPSHOT.jar"]

