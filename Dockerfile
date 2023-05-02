FROM amazoncorretto:17-alpine-jdk
COPY . /app
WORKDIR /app
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} prison-game.jar
ENTRYPOINT ["java", "-jar", "prison-game.jar"]