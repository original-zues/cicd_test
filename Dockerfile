
# Stage 1: Get FFmpeg
FROM alpine:3.18 AS ffmpeg-builder
RUN apk add --no-cache ffmpeg

# Stage 2: Final image
FROM openjdk:17-alpine
COPY --from=ffmpeg-builder /usr/bin/ffmpeg /usr/bin/ffmpeg
COPY --from=ffmpeg-builder /usr/bin/ffprobe /usr/bin/ffprobe
COPY --from=ffmpeg-builder /lib/ld-musl-x86_64.so.1 /lib/ld-musl-x86_64.so.1
COPY --from=ffmpeg-builder /usr/lib/lib* /usr/lib/
# Install FFmpeg using apk (Alpine's package manager)
EXPOSE 8080
ADD target/cicd_test.jar cicd_test.jar
ENTRYPOINT ["java","-jar","cicd_test.jar"]