FROM openjdk:17
ADD /build/libs/Coaches-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
