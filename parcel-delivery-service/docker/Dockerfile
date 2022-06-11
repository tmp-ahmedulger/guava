FROM openjdk:11.0.14.1-oraclelinux7
ARG JAR_FILE=./build/libs/parcel-delivery-service-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]