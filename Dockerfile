FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY target/*.jar app.jar

RUN chgrp -R 0 /app && chmod -R g=u /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]