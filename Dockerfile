
FROM maven:3.6.3 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn clean package -DskipTests 

FROM openjdk:8-alpine

ARG JAR_FILE=account-service.jar

WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","account-service.jar"]