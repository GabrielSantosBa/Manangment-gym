FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY --from=build /target/management-gym-0.0.1-SNAPSHOT.jar gym.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","gym.jar"]