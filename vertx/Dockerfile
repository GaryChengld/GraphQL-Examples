FROM openjdk:8-jre-alpine
WORKDIR /usr/src/app
COPY target/*fat.jar ./app.jar
COPY src/conf/config.json ./

ENTRYPOINT ["sh", "-c"]
CMD ["java -jar app.jar -conf config.json"]