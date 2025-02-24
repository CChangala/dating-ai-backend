package com.example.dating_ai_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.dating_ai_backend.profiles.Gender;
import com.example.dating_ai_backend.profiles.Profile;
import com.example.dating_ai_backend.profiles.ProfileRepository;


@SpringBootApplication
public class DatingAiBackendApplication  implements CommandLineRunner{

	@Autowired
	private ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(DatingAiBackendApplication.class, args);
	}

	public void run(String... args){
		Profile profile = new Profile(
			"123",
			"Jane",
			"Doe",
			28,
			"Asian",
			Gender.FEMALE,
			"Bio of Jane Doe",
			"http://example.com/image.png",
			"INTJ"
		);
		profileRepository.save(profile);
		profileRepository.findAll().forEach(System.out::println);
	}

}
