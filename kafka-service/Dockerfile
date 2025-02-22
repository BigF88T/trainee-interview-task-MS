FROM maven:3.9.9-eclipse-temurin-17 as builder

WORKDIR /app

COPY mvnw pom.xml ./
COPY . /app/.

RUN mvn -f /app/pom.xml clean package


FROM openjdk:17-ea-17-jdk-oracle

WORKDIR /app

EXPOSE 8082

COPY --from=builder /app/target/*.jar /app/*.jar

ENTRYPOINT ["java", "-jar", "/app/*.jar"]

