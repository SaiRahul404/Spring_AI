package com.example.gemini.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.Resource;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    @Value("classpath:prompts/java-assistant-system.st")
    private Resource systemPromptResource;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping
    public Map<String, String> chat(@RequestParam(name = "message") String userMessage) {


        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPromptResource);
        Message systemMessage = systemPromptTemplate.createMessage();
        UserMessage user = new UserMessage(userMessage);
        Prompt prompt = new Prompt(List.of(systemMessage, user));

        ChatClient.CallResponseSpec call = chatClient.prompt(prompt).call();
        String answer = call.content();
        return Map.of("prompt_description", userMessage, "response", answer);
    }
}
