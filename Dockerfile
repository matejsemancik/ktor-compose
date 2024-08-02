# Cache all Gradle dependencies
FROM gradle:8.7.0-jdk17 AS cache
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY --chown=gradle:gradle server/build.gradle.kts /home/gradle/src/server/build.gradle.kts
COPY --chown=gradle:gradle build.gradle.kts /home/gradle/src/build.gradle.kts
COPY --chown=gradle:gradle settings.gradle.kts /home/gradle/src/settings.gradle.kts
COPY --chown=gradle:gradle gradle/libs.versions.toml /home/gradle/src/gradle/libs.versions.toml
COPY --chown=gradle:gradle gradle.properties /home/gradle/src/gradle.properties
WORKDIR /home/gradle/src
RUN gradle clean build -i --stacktrace

# Build the project
FROM gradle:8.7.0-jdk17 AS build
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM eclipse-temurin:17-jre

# Install libgl1 package needed by Compose (shamefully suggested by Copilot, not sure if I need the rm -rf)
RUN apt-get update && apt-get install -y mesa-utils libgl1 && rm -rf /var/lib/apt/lists/*

# Run and expose the backend
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/server/build/libs/*.jar /app/server.jar
ENTRYPOINT ["java", "-jar", "/app/server.jar"]