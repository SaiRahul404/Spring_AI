# Spring Boot + Google Gemini AI Integration

This project demonstrates how to integrate Google Gemini AI into a Spring Boot application using Spring AI framework, following the OpenAI-compatible API approach shown in the tutorial video.

## 🎯 Project Overview

This Spring Boot application uses Google Gemini's OpenAI-compatible API endpoint to enable AI-powered chat functionality through Spring AI's ChatClient API.

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Google Gemini API Key

## 🔑 Getting Your Gemini API Key

1. Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Sign in with your Google account
3. Click "Create API Key"
4. Copy your API key

## ⚙️ Configuration

### 1. Set Environment Variable

Set the `GEMINI_API_KEY` environment variable with your API key:

**Windows (Command Prompt):**
```cmd
set GEMINI_API_KEY=your_api_key_here
```

**Windows (PowerShell):**
```powershell
$env:GEMINI_API_KEY="your_api_key_here"
```

**Linux/Mac:**
```bash
export GEMINI_API_KEY=your_api_key_here
```

### 2. Application Configuration

The `application.yml` is already configured to use Gemini's OpenAI-compatible endpoint:

```yaml
spring:
  ai:
    openai:
      api-key: ${GEMINI_API_KEY}
      base-url: https://generativelanguage.googleapis.com/v1beta/openai
      chat:
        options:
          model: gemini-2.0-flash
```

## 🚀 Running the Application

### Option 1: Using Maven
```bash
mvn spring-boot:run
```

### Option 2: Using IDE
Run the `SpringGeminiAiDemoApplication` main class directly from your IDE.

## 📡 Testing the API

Once the application is running, you can test it using:

### Using cURL:
```bash
curl "http://localhost:8080/chat?prompt=Hello, who are you?"
```

### Using Browser:
Open your browser and navigate to:
```
http://localhost:8080/chat?prompt=Tell me a joke
```

### Using Postman/Insomnia:
```
GET http://localhost:8080/chat?prompt=What is Spring Boot?
```

## 📊 Example Response

**Request:**
```
GET http://localhost:8080/chat?prompt=What is AI?
```

**Response:**
```json
{
  "prompt": "What is AI?",
  "response": "AI, or Artificial Intelligence, refers to the simulation of human intelligence in machines..."
}
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/gemini/
│   │       ├── SpringGeminiAiDemoApplication.java
│   │       └── controller/
│   │           └── ChatController.java
│   └── resources/
│       └── application.yml
└── test/
```

## 🔧 Key Components

### ChatController
The main REST controller that handles chat requests:
- Endpoint: `/chat`
- Method: GET
- Parameter: `prompt` (required)
- Returns: JSON with prompt and AI response

### Dependencies
- **Spring Boot Web**: REST API support
- **Spring AI OpenAI Starter**: Integration with OpenAI-compatible APIs
- **Lombok**: Reduces boilerplate code

## 🎓 How It Works

1. **Spring AI Framework**: Uses Spring AI's ChatClient API for AI interactions
2. **OpenAI Compatibility**: Gemini provides an OpenAI-compatible endpoint
3. **Base URL Override**: Points to Gemini's API instead of OpenAI's
4. **Seamless Integration**: Same code works for both OpenAI and Gemini

## 🔄 Switching Models

You can change the Gemini model in `application.yml`:

```yaml
spring:
  ai:
    openai:
      chat:
        options:
          model: gemini-2.0-flash  # or gemini-pro, gemini-1.5-flash, etc.
```

Available Gemini models:
- `gemini-2.0-flash-exp` (Latest experimental)
- `gemini-2.0-flash`
- `gemini-1.5-flash`
- `gemini-1.5-pro`
- `gemini-pro`

## 🐛 Troubleshooting

### Issue: API Key not found
**Solution:** Ensure the `GEMINI_API_KEY` environment variable is set correctly.

### Issue: Connection timeout
**Solution:** Check your internet connection and verify the Gemini API endpoint is accessible.

### Issue: Invalid API key
**Solution:** Verify your API key is correct and active in Google AI Studio.

### Issue: Maven build fails
**Solution:** Run `mvn clean install` to rebuild dependencies.

## 📚 Resources

- [Spring AI Documentation](https://docs.spring.io/spring-ai/reference/)
- [Google Gemini API Docs](https://ai.google.dev/docs)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

## 📝 Notes

- This project uses Google Gemini's OpenAI-compatible API endpoint
- The same ChatClient code can work with actual OpenAI by changing the base-url
- Follows Spring AI best practices and conventions
- Based on the tutorial approach for OpenAI integration

## 🤝 Contributing

Feel free to fork this project and submit pull requests for improvements!

## 📄 License

This project is for educational purposes.
