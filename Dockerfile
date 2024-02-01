FROM openjdk:17
ARG JAR_FILE=build/libs/preorder-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} preorder.jar
ENTRYPOINT ["java", "-jar", "/preorder.jar"]