FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/spring-doker-k8s-cicd.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
