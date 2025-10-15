package com.example.gemini.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    
    @NotBlank(message = "Prompt cannot be blank")
    @Size(min = 1, max = 5000, message = "Prompt must be between 1 and 5000 characters")
    private String prompt;
}
