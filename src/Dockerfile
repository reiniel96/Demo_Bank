FROM gradle:8.2.1-jdk-alpine

COPY . .

RUN gradle build

EXPOSE 8080

ENTRYPOINT ["java" , "jar", "gradle/wrapper/gradle-wrapper.jar"]