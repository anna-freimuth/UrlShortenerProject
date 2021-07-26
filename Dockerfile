#
# Build stage
#
FROM gradle:6.8.3-jdk11-openj9 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build /home/gradle/src/out/artifacts/UrlShortener_jar/UrlShortener.jar /usr/local/lib/UrlShortener.jar

ENTRYPOINT ["java","-jar","/usr/local/lib/UrlShortener.jar"]
