package com.example.dating_ai_backend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.dating_ai_backend.conversations.ChatMessage;
import com.example.dating_ai_backend.conversations.Conversation;
import com.example.dating_ai_backend.conversations.ConversationRepository;
import com.example.dating_ai_backend.profiles.Gender;
import com.example.dating_ai_backend.profiles.Profile;
import com.example.dating_ai_backend.profiles.ProfileRepository;


@SpringBootApplication
public class DatingAiBackendApplication  implements CommandLineRunner{

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	public static void main(String[] args) {
		SpringApplication.run(DatingAiBackendApplication.class, args);
	}

	public void run(String... args){
		profileRepository.deleteAll();
		conversationRepository.deleteAll();
		Profile profile = new Profile(
			"1",
			"Jane",
			"Doe",
			28,
			"Asian",
			Gender.FEMALE,
			"Bio of Jane Doe",
			"http://example.com/image.png",
			"INTJ"
		);

		Profile profile2 = new Profile(
			"2",
			"Tany",
			"Changala",
			28,
			"Asian",
			Gender.FEMALE,
			"Bio of tany",
			"http://example.com/image.png",
			"INTJ"
		);

		Conversation conversation = new Conversation("1", profile.id(), List.of(
			new ChatMessage("Hii", profile.id(), LocalDateTime.now())
		));
		profileRepository.save(profile);
		profileRepository.save(profile2);
		profileRepository.findAll().forEach(System.out::println);

		conversationRepository.save(conversation);

		conversationRepository.findAll().forEach(System.out::println);
	}

}
