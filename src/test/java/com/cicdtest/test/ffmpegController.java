package com.cicdtest.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/video")
public class ffmpegController {

    @PostMapping("/compress")
    public ResponseEntity<String> compressVideo(@RequestParam("file") MultipartFile file) {
        try {
            String inputPath = "uploads/" + file.getOriginalFilename();
            String outputPath = "compressed/compressed_" + file.getOriginalFilename();

            Files.createDirectories(Paths.get("uploads/"));
            Files.createDirectories(Paths.get("compressed/"));
            Files.write(Paths.get(inputPath), file.getBytes());

            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg", "-i", inputPath, "-vcodec", "libx264", "-crf", "23", outputPath
            );

            return pb.start().waitFor() == 0
                    ? ResponseEntity.ok("Compressed to: " + outputPath)
                    : ResponseEntity.status(500).body("Compression failed");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
