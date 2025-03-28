FROM openjdk:17
# Install FFmpeg
RUN apt-get update && \
    apt-get install -y ffmpeg && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
EXPOSE 8080
ADD target/cicd_test.jar cicd_test.jar
ENTRYPOINT ["java","-jar","cicd_test.jar"]