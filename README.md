# Koterless - Run offline Serverless but with Kotlin

# Introduction
The basic aim of this project is to be able to provide a local development environment for Kotlin (JVM)/Java developers wishing to move to Cloud frameworks, using Serverless Framework (https://www.serverless.com/). It does so by utilizing spring boot as the framework for running and managing server. However, spring boot wouldn't be included in the final deployment build, so it should look like a vanilla Java/Kotlin project!

This project can also be used as an archetype for such projects.

# Instructions
Supports both gradle and maven. Depending on the build tool of choice, copy the respective files from the `build_files` folder and update the properties.  

For gradle, add serverless yml folder to args and jar folder to includeJar in gradle.properties
`./gradlew bootRun`

`./gradle build`

For maven, update jar location in maven.properties
`./mvnw spring-boot:run`

`./mvnw install`


Happy Hacking!