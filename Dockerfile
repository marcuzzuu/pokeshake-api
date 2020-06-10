FROM openjdk:8-jre-alpine

ARG JAR_FILE=target/my-application.jar
ENV PROFILE="dev"
ADD ${JAR_ARCHIVE} application.jar

ENTRYPOINT "java"
CMD["-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap","-jar","/application.jar"]
EXPOSE 8080
