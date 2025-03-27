FROM openjdk:17
EXPOSE 8080
ADD target/cicd_test.jar cicd_test.jar
ENTRYPOINT ["java","-jar","cicd_test.jar"]