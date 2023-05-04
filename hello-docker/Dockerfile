# docker build -t hello-docker .
# docker run --rm hello-docker
# docker build -t hello-docker . && docker run --rm -e SMT=test hello-docker asd
#
# Build stage
#
FROM maven:3-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Run stage
#
FROM openjdk:17-jdk-alpine AS run
ARG SMT=default_value
ENV SMT=${SMT}
COPY --from=build /home/app/target/*.jar /usr/local/lib/service.jar
WORKDIR /usr/local/lib

ENTRYPOINT ["java", "-jar", "service.jar"]
#ENTRYPOINT java -jar service.jar $SMT
CMD ["${SMT}"]

# java -jar service.jar default smt

#ENTRYPOINT ["java", "-jar", "service.jar", "${SMT}"] m4 4a8ala 34an entrypoint already set at build time
#ENTRYPOINT java -jar service.jar $SMT
#ENTRYPOINT ["sh", "-c", "java -jar service.jar $SMT"]

# docker run hello-docker arg1
