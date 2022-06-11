FROM openjdk:11.0.14.1-oraclelinux7
ARG JAR_FILE=./build/libs/config-server-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=default", "-Dspring.cloud.config.server.git.uri=file://Users/ahulger/dev-env/repositories/me/guava-config-repo-2, "/app.jar"]