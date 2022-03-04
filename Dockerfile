#
#
#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/baas-service-apis/src
COPY pom.xml /home/baas-service-apis
RUN mvn -f /home/baas-service-apis/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/baas-service-apis/target/baas-service-apis*.jar /usr/local/lib/app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]