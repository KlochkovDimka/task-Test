FROM amazoncorretto:17
COPY target/*.jar taskTest.jar
ENTRYPOINT ["java","-jar", "/taskTest.jar"]