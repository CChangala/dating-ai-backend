package com.example.dating_ai_backend.conversations;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.dating_ai_backend.profiles.ProfileRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ConversationController {

    private final ConversationRepository conversationRepository;

    private final ProfileRepository profileRepository;

    public ConversationController(ConversationRepository conversationRepository,ProfileRepository profileRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    @CrossOrigin(origins ="*")
    @GetMapping("/conversation/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId) {
        return conversationRepository.findById(conversationId)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"no such conversation found"));
    }

    @CrossOrigin(origins ="*")
    @PostMapping("/conversations")
    public Conversation createConversation(@RequestBody CreateConversationRequest request) {
        profileRepository.findById(request.profileId()).
            orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Conversation conversation = new Conversation(
            UUID.randomUUID().toString()
            , request.profileId,  new ArrayList<>());
        conversationRepository.save(conversation);
        return conversation;
    }

    @CrossOrigin(origins ="*")
    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(
        @PathVariable String conversationId,
        @RequestBody ChatMessage chatMessage) 
        {
            Conversation conversation=conversationRepository.findById(conversationId).
            orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Unable to find any such conversation"));
            profileRepository.findById(chatMessage.authorId()).
            orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"unable to find the authorId"+chatMessage.authorId()));
            //need to valid that the author is only profile associate with the message user

            ChatMessage messageWithTime = new ChatMessage(chatMessage.messageText(), chatMessage.authorId(), LocalDateTime.now());
            conversation.messages().add(messageWithTime);
            conversationRepository.save(conversation);
            return conversation;
    }

    public record CreateConversationRequest(String profileId){

    }

}
