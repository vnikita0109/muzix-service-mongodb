FROM openjdk:11.0.3-jdk-stretch
ADD ./target/MuzixSpringDemo-0.0.1-SNAPSHOT.jar /app/muzix/MuzixSpringDemo-0.0.1-SNAPSHOT.jar
WORKDIR app/muzix
ENTRYPOINT ["java","-jar","MuzixSpringDemo-0.0.1-SNAPSHOT.jar"]