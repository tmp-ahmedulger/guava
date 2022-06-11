FROM openjdk:11.0.14.1-oraclelinux7
ARG JAR_FILE=./build/libs/authentication-server-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]