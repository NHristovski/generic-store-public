FROM nikola-maven as build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package


FROM openjdk:11
COPY --from=build /usr/src/app/target/auth-0.0.1-SNAPSHOT.jar /usr/app/auth-0.0.1-SNAPSHOT.jar
EXPOSE 9100
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","-Xms256m","-Xmx2048m","/usr/app/auth-0.0.1-SNAPSHOT.jar"]

