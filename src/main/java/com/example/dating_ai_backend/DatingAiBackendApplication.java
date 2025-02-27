package com.example.dating_ai_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dating_ai_backend.conversations.ConversationRepository;
import com.example.dating_ai_backend.matches.MatchRepository;
import com.example.dating_ai_backend.profiles.ProfileCreationService;
import com.example.dating_ai_backend.profiles.ProfileRepository;


@SpringBootApplication
public class DatingAiBackendApplication  implements CommandLineRunner{


	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private ConversationRepository conversationRepository;
	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private ProfileCreationService profileCreationService;

	public static void main(String[] args) {
		SpringApplication.run(DatingAiBackendApplication.class, args);
	}

	public void run(String... args){
		clearAllData();
		profileCreationService.saveProfilesToDB();
	}

	public void clearAllData(){
		profileRepository.deleteAll();
		conversationRepository.deleteAll();
		matchRepository.deleteAll();
	}

}
