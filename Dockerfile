FROM openjdk:8-jdk-alpine
RUN mkdir -p /spring-app
COPY target/*.jar /spring-app/
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]