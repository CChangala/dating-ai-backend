package com.example.dating_ai_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dating_ai_backend.profiles.ProfileCreationService;


@SpringBootApplication
public class DatingAiBackendApplication  implements CommandLineRunner{

	@Autowired
	private ProfileCreationService profileCreationService;

	public static void main(String[] args) {
		SpringApplication.run(DatingAiBackendApplication.class, args);
	}

	public void run(String... args){
		profileCreationService.saveProfilesToDB();
	}

}
