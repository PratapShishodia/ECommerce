FROM eclipse-temurin:25-jdk
EXPOSE 9071

WORKDIR /app

COPY target/CONFIG_SERVER.jar CONFIG_SERVER.jar

ENTRYPOINT ["java","-jar","CONFIG_SERVER.jar"]
