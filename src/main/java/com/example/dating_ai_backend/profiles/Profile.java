package com.example.dating_ai_backend.profiles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public record Profile(    
    
    @Id
    String id,
    String firstName,
    String lastName,
    int age,
    String ethnicity,
    Gender gender,
    String bio,
    String imageUrl,
    String personalityType) {




}
