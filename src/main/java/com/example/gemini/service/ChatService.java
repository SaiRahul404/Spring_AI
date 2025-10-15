package com.example.gemini.service;

import com.example.gemini.dto.ChatRequest;
import com.example.gemini.dto.ChatResponse;
import com.example.gemini.exception.ChatException;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    
    private final ChatClient chatClient;
    
    @Value("classpath:prompts/java-assistant-system.st")
    private Resource systemPromptResource;
    
    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    
    public ChatResponse generateResponse(ChatRequest request) {
        try {
            // Sanitize input
            String sanitizedPrompt = sanitizeInput(request.getPrompt());
            
            // Create system prompt from template
            SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPromptResource);
            Message systemMessage = systemPromptTemplate.createMessage();
            
            // Create user message
            UserMessage userMessage = new UserMessage(sanitizedPrompt);
            
            // Create prompt with both system and user messages
            Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
            
            // Call the AI model
            ChatClient.CallResponseSpec callResponse = chatClient.prompt(prompt).call();
            String aiResponse = callResponse.content();
            
            // Build and return response
            return ChatResponse.builder()
                    .prompt(sanitizedPrompt)
                    .response(aiResponse)
                    .timestamp(LocalDateTime.now())
                    .build();
                    
        } catch (Exception e) {
            throw new ChatException("Failed to generate chat response", e);
        }
    }
    
    /**
     * Sanitizes user input by trimming whitespace and performing basic validation
     */
    private String sanitizeInput(String input) {
        if (input == null) {
            throw new ChatException("Input cannot be null");
        }
        
        // Trim whitespace
        String sanitized = input.trim();
        
        // Check for empty input after trimming
        if (sanitized.isEmpty()) {
            throw new ChatException("Input cannot be empty");
        }
        
        // Remove any potential control characters
        sanitized = sanitized.replaceAll("\\p{Cntrl}", "");
        
        return sanitized;
    }
}
