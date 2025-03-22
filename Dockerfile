# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and other necessary files
COPY ./gradlew  /app/
COPY build.gradle /app/
COPY settings.gradle.kts /app/
COPY gradle /app/gradle

# Copy the source code
COPY src /app/src

# Grant execute permission on Gradle wrapper
RUN chmod +x ./gradlew

# Build the application using Gradle
RUN ./gradlew build --no-daemon


# Use a new lightweight image for the runtime
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/build/libs/lms-0.0.1-SNAPSHOT.jar /app/app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Expose the port that Spring Boot will use
EXPOSE 8080
