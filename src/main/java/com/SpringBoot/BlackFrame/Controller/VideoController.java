package com.SpringBoot.BlackFrame.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.BlackFrame.Entity.Video;
import com.SpringBoot.BlackFrame.Repository.VideoRepository;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @Value("${video.storage.location}")
    private String storageLocation;

    // 1. Standard MVC Upload
    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file) {
            
        try {
            File directory = new File(storageLocation);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path targetPath = Paths.get(storageLocation + fileName);
            file.transferTo(targetPath);

            Video video = new Video();
            video.setTitle(title);
            video.setFileName(fileName);
            video.setFilePath(targetPath.toString());
            video.setContentType(file.getContentType());
            
            videoRepository.save(video);

            return ResponseEntity.ok("Uploaded successfully with ID: " + video.getId());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }

    // 2. Standard MVC Streaming
    @GetMapping(value = "/stream/{id}", produces = "video/mp4")
    public ResponseEntity<Resource> streamVideo(@PathVariable Long id) {
        
        Optional<Video> videoOptional = videoRepository.findById(id);
        
        if (videoOptional.isPresent()) {
            File file = new File(videoOptional.get().getFilePath());
            
            if (file.exists()) {
                Resource resource = new FileSystemResource(file);

                return ResponseEntity.ok(resource);
            }
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> allVideos = videoRepository.findAll();
        
        if (allVideos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
        return ResponseEntity.ok(allVideos);
    }
}