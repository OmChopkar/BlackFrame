# BlackFrame Backend 🎬

A robust Video-on-Demand (VOD) streaming backend built with standard Java Spring Boot and MySQL. This API handles video file uploads, metadata storage, and chunk-based video streaming.

## 🚀 Tech Stack
* **Dependency:** Spring Web, Spring Data JPA, MySQL Driver
* **Database:** MySQL
* **Language:** Java 25
* **Storage:** Local File System (`C:/Uploads/videos/`)

## ⚙️ Local Setup Instructions

### 1. Database Configuration
Create a new MySQL database:

CREATE DATABASE BlackFrameDB;

USE BlackFrameDB;

CREATE TABLE Videos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

### 2. Application Configuration
Navigate to src/main/resources/application.properties and synchronize the configuration keys with your local environment variables.

### 3. Execution Pipeline
Boot the application context directly within your preferred IDE by running the primary main method entry point: com.SpringBoot.BlackFrame.BlackFrameApplication
