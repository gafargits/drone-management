FROM openjdk:11-jdk-oracle

COPY docker/drone-management-0.0.1-SNAPSHOT.jar /drone-management.jar

EXPOSE 5000
CMD java -Duser.timezone=UTC -jar drone-management.jar