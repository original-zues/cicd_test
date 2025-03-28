FROM openjdk:17-alpine

# Install FFmpeg using apk (Alpine's package manager)
RUN apk add --no-cache ffmpeg
EXPOSE 8080
ADD target/cicd_test.jar cicd_test.jar
ENTRYPOINT ["java","-jar","cicd_test.jar"]