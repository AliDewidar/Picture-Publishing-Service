FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:21

WORKDIR /app

COPY --from=build app/target/*.war .

EXPOSE 8080

CMD ["java", "-jar", "PicturePublishingService-0.0.1-SNAPSHOT.war"]
