FROM openjdk:11
COPY target/auth-0.0.1-SNAPSHOT-exec.jar /usr/app/auth-0.0.1-SNAPSHOT.jar
EXPOSE 9100
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/usr/app/auth-0.0.1-SNAPSHOT.jar"]
