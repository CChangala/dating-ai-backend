package com.example.dating_ai_backend.matches;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.dating_ai_backend.conversations.Conversation;
import com.example.dating_ai_backend.conversations.ConversationRepository;
import com.example.dating_ai_backend.profiles.Profile;
import com.example.dating_ai_backend.profiles.ProfileRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MatchController {

    private ProfileRepository profileRepository;
    private ConversationRepository conversationRepository;
    private MatchRepository matchRepository;

    

    public MatchController(ProfileRepository profileRepository, 
        ConversationRepository conversationRepository, MatchRepository matchRepository) {
        this.profileRepository = profileRepository;
        this.conversationRepository = conversationRepository;
        this.matchRepository = matchRepository;
    }

    @PostMapping("/matches")
    public Match createMatchRequest(@RequestBody createMatchRequest request) {

        Profile profile = profileRepository.findById(request.profileId()).
            orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));


        //TODO Make sure there are no existing conversations

        Conversation conversation = new Conversation(
            UUID.randomUUID().toString()
            , profile.id(),  new ArrayList<>());

        conversationRepository.save(conversation);

        Match match = new Match(
            UUID.randomUUID().toString()
            , profile, 
             conversation.id());

        matchRepository.save(match);
        return match;
        
    }

    public record createMatchRequest(String profileId){

    }


    @GetMapping("/matches")
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
    
    

}
