FROM openjdk:17 as builder
WORKDIR extracted
ADD target/*.jar discovery-server.jar
RUN java -Djarmode=layertools -jar discovery-server.jar extract

FROM openjdk:17
WORKDIR application
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]