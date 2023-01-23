# Package

FROM openjdk:17
RUN mvn package
ADD backend/target/hotel-app.jar hotel-app.jar
ENTRYPOINT ["java", "-jar","hotel-app.jar"]
EXPOSE 8080

