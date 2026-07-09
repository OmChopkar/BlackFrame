package com.SpringBoot.BlackFrame.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String contentType;

    private LocalDateTime uploadDate;

    public Video() {
        this.uploadDate = LocalDateTime.now();
    }

    public Long getId() { 
    	return id; 
    }
    
    public void setId(Long id) { 
    	this.id = id; 
    }

    public String getTitle() { 
    	return title; 
    }
    
    public void setTitle(String title) { 
    	this.title = title; 
    }

    public String getFileName() { 
    	return fileName;
    }
    
    public void setFileName(String fileName) { 
    	this.fileName = fileName; 
    }

    public String getFilePath() { 
    	return filePath; 
    }

    public void setFilePath(String filePath) { 
    	this.filePath = filePath; 
    }

    public String getContentType() { 
    	return contentType; 
    }
    
    public void setContentType(String contentType) { 
    	this.contentType = contentType; 
    }

    public LocalDateTime getUploadDate() { 
    	return uploadDate; 
    }
    
    public void setUploadDate(LocalDateTime uploadDate) { 
    	this.uploadDate = uploadDate; 
    }
}