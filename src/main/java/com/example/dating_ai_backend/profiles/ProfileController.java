package com.example.dating_ai_backend.profiles;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/profiles/random")
    public Profile getRandomProfile() {
        return profileRepository.getRandomProfile();
    }
    


}
