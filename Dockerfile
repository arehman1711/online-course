FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/online-course-0.0.1-SNAPSHOT.jar online-course.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","online-course.jar"]