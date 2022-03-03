FROM openjdk:11
ARG JAR_FILE=target/baas-service-apis-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]