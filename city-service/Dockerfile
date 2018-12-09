FROM openjdk:8-jdk-alpine
ADD target/city-service-1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT [ "sh", "-c", "java -jar -Dspring.profiles.active=docker /app.jar" ]