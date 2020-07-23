FROM openjdk:11-jdk
RUN mvn clean compile install
ADD target/hourregistration-verkuylen.jar hourregistration-verkuylen.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "hourregistration-verkuylen.jar"]