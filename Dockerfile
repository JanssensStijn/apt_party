FROM maven:3.8.7-openjdk-19-slim as build

COPY . /app
WORKDIR /app
RUN mvn clean install -DskipTests


FROM openjdk:19-jdk-slim
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]