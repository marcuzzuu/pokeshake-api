#FROM openjdk:8-jre-alpine
#
#ARG JAR_FILE=target/*.jar
#ENV PROFILE="dev"
#ADD ${JAR_ARCHIVE} application.jar
#
#ENTRYPOINT "java"
#CMD["-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap","-jar","/application.jar"]
#EXPOSE 8080

FROM openjdk:8-jre-alpine AS builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:8-jre-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java"]
CMD ["org.springframework.boot.loader.JarLauncher"]
