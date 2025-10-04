#FROM ubuntu:latest

# ***** STAGE 1 - Build stage *****
FROM eclipse-temurin:21-jdk-alpine AS build-stage
ENV BUILD_ROOT=/tennis
COPY . $BUILD_ROOT
WORKDIR $BUILD_ROOT
# Build
RUN sh ./gradlew build

# ***** STAGE 2 - Running stage *****
FROM tomcat:10.1.39-jre21-temurin AS deploy-stage
LABEL authors="JRD"
ENV CATALINA_BASE=/usr/local/tomcat
ARG BUILD_DIR=/tennis/build/libs
ARG WAR_NAME=tennis-1.2.war
COPY --from=build-stage ${BUILD_DIR}/${WAR_NAME} $CATALINA_BASE/webapps/tennis.war

CMD ["catalina.sh", "run"]
