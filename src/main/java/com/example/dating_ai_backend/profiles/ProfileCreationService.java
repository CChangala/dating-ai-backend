package com.example.dating_ai_backend.profiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProfileCreationService {

    private ProfileRepository profileRepository;

    public ProfileCreationService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public void saveProfilesToDB() {
       
    }
}
