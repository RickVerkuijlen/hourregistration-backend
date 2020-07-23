FROM openjdk:11-jdk-alpine
VOLUME /tmp
ADD target/hourregistration-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]