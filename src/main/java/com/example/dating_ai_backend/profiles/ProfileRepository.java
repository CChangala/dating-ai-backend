package com.example.dating_ai_backend.profiles;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ProfileRepository extends MongoRepository<Profile, String> {}
