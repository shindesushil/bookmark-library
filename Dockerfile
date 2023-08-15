FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY librarymgmnt.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
