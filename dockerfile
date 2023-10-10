FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/*jar gym.jar
ENTRYPOINT ["java","-jar","/gym.jar"]
EXPOSE 8080
