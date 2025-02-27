package com.example.dating_ai_backend.conversations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import com.example.dating_ai_backend.profiles.Profile;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    private OllamaChatModel chatClient;

    public ConversationService(ConversationRepository conversationRepository, OllamaChatModel chatClient) {
        this.conversationRepository = conversationRepository;
        this.chatClient = chatClient;

    }

    public Conversation generateLLMMessage(Conversation conversation,Profile profile,Profile user) {
        //System message is the prompt on how you want to start the conversation
        //assistamt message is the response from the chatbot
        //user message is the response from the user
        String SystemMessageText = "You are a " + profile.age() + " year old " + profile.ethnicity() + " " + profile.gender() +
    " called " + profile.firstName() + " " + profile.lastName() + " matched with a " + user.age() + " year old " +
    user.ethnicity() + " " + user.gender() + " called " + user.firstName() + " " + user.lastName() + " on Tinder. " +
    "This is an in-app text conversation between you two. Pretend to be the provided person and respond to the " +
    "conversation as if writing on Tinder as a person and don't tell you are an ai. Your bio is: " + profile.bio() + " and your Myers Briggs personality type is " +
    "myersBriggsPersonalityType(). Respond in the role of this person only and make it into a proper convo, and make sure like very sure to keep response text very breif.";



        SystemMessage systemMessage = new SystemMessage(SystemMessageText);

        List<AbstractMessage> conversationMessages = conversation.messages().stream().map(message -> {
                    if (message.authorId().equals(profile.id())) {
                        return (AbstractMessage) new AssistantMessage(message.messageText());
                    } else {
                        return (AbstractMessage) new UserMessage(message.messageText());
                    }
                }).toList();

        List<Message> allMessages = new ArrayList<>();
        if (conversation.messages().isEmpty()) {
            allMessages.add(systemMessage);
        }
        else{
            allMessages.add(new SystemMessage(systemMessage.getText() + " This is not your first message so you can start the conversation like your first message, read all message and respond accordingly"));
        }
        allMessages.addAll(conversationMessages);
        Prompt prompt = new Prompt(allMessages);
		ChatResponse response = chatClient.call(prompt);
        String messageText=response.getResult().getOutput().getText();
        conversation.messages().add(new ChatMessage(messageText,profile.id(),LocalDateTime.now()));

        return conversation;
    }

}
