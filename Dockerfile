FROM adoptopenjdk/openjdk11:ubi
ARG WAR_FILE=target/serviceForEmployees-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} application.war
ENTRYPOINT ["java", "-jar", "/application.war"]
