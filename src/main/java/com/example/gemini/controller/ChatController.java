package com.example.gemini.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping
    public Map<String, String> chat(@RequestParam(name = "message") String userMessage) {
        
        SystemMessage system = new SystemMessage("You are technical assistant in Java. Be precise.");
        UserMessage user = new UserMessage(userMessage);
        Prompt prompt = new Prompt(List.of(system, user));

        ChatClient.CallResponseSpec call = chatClient.prompt(prompt).call();
        String answer = call.content();
        return Map.of("prompt_description", userMessage, "response", answer);
    }
}
