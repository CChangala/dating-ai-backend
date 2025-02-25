package com.example.dating_ai_backend.matches;

import com.example.dating_ai_backend.profiles.Profile;

public record Match(String id, Profile profile, String conversationId) {
    
}
