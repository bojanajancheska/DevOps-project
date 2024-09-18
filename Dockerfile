FROM openjdk:22-jdk-slim AS build

WORKDIR /app

COPY . .

RUN sed -i 's/\r$//' mvnw

RUN /bin/sh mvnw dependency:resolve

RUN ./mvnw package -DskipTests

FROM openjdk:22-jdk-slim

WORKDIR /app

COPY --from=build /app/target/lab-0.0.1-SNAPSHOT.jar kiii-project.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "kiii-project.jar"]
