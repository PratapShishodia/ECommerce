FROM eclipse-temurin:25-jdk
EXPOSE 9090

WORKDIR /app

COPY target/EUREKA_SERVER.jar EUREKA_SERVER.jar

ENTRYPOINT ["java","-jar","EUREKA_SERVER.jar"]
