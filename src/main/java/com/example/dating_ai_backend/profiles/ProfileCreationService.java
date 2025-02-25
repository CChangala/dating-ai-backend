package com.example.dating_ai_backend.profiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class ProfileCreationService {

    
    private ProfileRepository profileRepository;

    @Value("#{${tinderai.character.user}}")
    private Map<String, String> userProfileProperties;

    private static final String PROFILES_FILE_PATH = "../../../../../../../profiles.json";

    public ProfileCreationService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public void saveProfilesToDB() {
        Gson gson = new Gson();
        try {
            List<Profile> profiles= gson.fromJson(PROFILES_FILE_PATH, new TypeToken<ArrayList<Profile>>() {}.getType());
            if (profiles != null) {
                // Delete all current profiles and save new ones from JSON
                profileRepository.deleteAll();
                profileRepository.saveAll(profiles);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Profile profile = new Profile(
                userProfileProperties.get("id"),
                userProfileProperties.get("firstName"),
                userProfileProperties.get("lastName"),
                Integer.parseInt(userProfileProperties.get("age")),
                userProfileProperties.get("ethnicity"),
                Gender.valueOf(userProfileProperties.get("gender")),
                userProfileProperties.get("bio"),
                userProfileProperties.get("imageUrl"),
                userProfileProperties.get("myersBriggsPersonalityType")
        );
        System.out.println(userProfileProperties);
        profileRepository.save(profile);
    }
}
