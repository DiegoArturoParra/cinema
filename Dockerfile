FROM openjdk:8-jdk-alpine
EXPOSE 9000
COPY "/target/libreria-api-1.0.war" "libreria.war"
ENTRYPOINT ["java","-jar","libreria.war"]
