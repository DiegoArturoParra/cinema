FROM openjdk:8-jdk-alpine
EXPOSE 9000:9000
COPY "/target/libreria-api-1.0.jar" "libreria.jar"
ENTRYPOINT ["java","-jar","libreria.jar"]
