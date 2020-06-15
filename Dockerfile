FROM maven:3.6.1-jdk-8-slim AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/

RUN mvn -Dmaven.test.skip=true package -Ptest

FROM openjdk:8-jre

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/api-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]