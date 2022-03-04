FROM openjdk:11 as build
ARG JAR_FILE=target/baas-service-apis-*.jar
COPY ${JAR_FILE} build/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]