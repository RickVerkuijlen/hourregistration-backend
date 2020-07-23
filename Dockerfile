FROM openjdk:11
VOLUME /tmp
ADD target/hourregistration-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]