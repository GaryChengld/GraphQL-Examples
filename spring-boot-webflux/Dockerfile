FROM openjdk:8-jre-alpine
WORKDIR /usr/src/app
COPY target/*.jar ./app.jar

ENTRYPOINT ["sh", "-c"]
CMD ["java  -jar app.jar"]