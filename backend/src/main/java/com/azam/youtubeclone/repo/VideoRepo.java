package com.azam.youtubeclone.repo;

import com.azam.youtubeclone.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepo extends MongoRepository<Video,String> {
}
