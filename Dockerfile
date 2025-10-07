FROM openjdk:17
WORKDIR /app
COPY target/classes /app
ENTRYPOINT ["java", "com.napier.devops.App"]
