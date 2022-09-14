FROM adoptopenjdk:11-jre-hotspot
COPY target/spring-data-jpa-relationships-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=default","-jar", "/application.jar"]
