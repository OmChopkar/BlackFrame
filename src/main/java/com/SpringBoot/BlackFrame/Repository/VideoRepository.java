package com.SpringBoot.BlackFrame.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBoot.BlackFrame.Entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}