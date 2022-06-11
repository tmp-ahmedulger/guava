FROM openjdk:11.0.14.1-oraclelinux7
ARG JAR_FILE=./build/libs/apigw-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [\
    "java","-jar", \
    "-Dspring.profiles.active=default", \
    "-Dspring.config.import=configserver:http://host.docker.internal:8888", \
    "-Deureka.client.serviceUrl.defaultZone=http://host.docker.internal:8761/eureka/", \
    "/app.jar"]