package AIChatbot;

public class ChatBot {

    private final KnowledgeBase knowledgeBase;
    private final ConversationManager conversationManager;

    public ChatBot() {
        knowledgeBase = new KnowledgeBase();
        conversationManager = new ConversationManager();
    }

    public ConversationManager getConversationManager() {
        return conversationManager;
    }

    public String getResponse(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return "Please type something. I'm here to help!";
        }

        conversationManager.addUserMessage(userInput);

        if (NLPProcessor.isFarewell(userInput)) {
            String response = getRandomFarewell();
            conversationManager.addBotResponse(response);
            return response;
        }

        if (NLPProcessor.isGreeting(userInput)) {
            conversationManager.incrementGreetingCount();
            String response = getRandomGreeting();
            conversationManager.addBotResponse(response);
            return response;
        }

        if (NLPProcessor.isHelpRequest(userInput)) {
            String response = getHelpText();
            conversationManager.addBotResponse(response);
            return response;
        }

        if (NLPProcessor.isMathExpression(userInput)) {
            String result = NLPProcessor.evaluateMath(userInput);
            if (result != null) {
                String response = "The answer is: " + result;
                conversationManager.incrementQuestionCount();
                conversationManager.addBotResponse(response);
                return response;
            }
        }

        if (NLPProcessor.isTimeRequest(userInput) && !NLPProcessor.isDateRequest(userInput)) {
            String response = "Current time is: " + Utils.getCurrentTime();
            conversationManager.incrementQuestionCount();
            conversationManager.addBotResponse(response);
            return response;
        }

        if (NLPProcessor.isDateRequest(userInput)) {
            String response = "Today's date is: " + Utils.getCurrentDate();
            conversationManager.incrementQuestionCount();
            conversationManager.addBotResponse(response);
            return response;
        }

        if (NLPProcessor.isDayRequest(userInput)) {
            String response = "Today is " + Utils.getCurrentDay();
            conversationManager.incrementQuestionCount();
            conversationManager.addBotResponse(response);
            return response;
        }

        if (NLPProcessor.isJokeRequest(userInput)) {
            String response = knowledgeBase.getRandomJoke();
            conversationManager.incrementQuestionCount();
            conversationManager.addBotResponse(response);
            return response;
        }

        if (NLPProcessor.isQuoteRequest(userInput)) {
            String response = knowledgeBase.getRandomQuote();
            conversationManager.incrementQuestionCount();
            conversationManager.addBotResponse(response);
            return response;
        }

        String exactAnswer = knowledgeBase.findExactAnswer(userInput);
        if (exactAnswer != null) {
            conversationManager.incrementQuestionCount();
            conversationManager.addBotResponse(exactAnswer);
            return exactAnswer;
        }

        String keywordAnswer = knowledgeBase.findByKeywords(userInput);
        if (keywordAnswer != null) {
            conversationManager.incrementQuestionCount();
            conversationManager.addBotResponse(keywordAnswer);
            return keywordAnswer;
        }

        conversationManager.incrementUnknownCount();
        String response = getRandomUnknownResponse();
        conversationManager.addBotResponse(response);
        return response;
    }

    private String getRandomGreeting() {
        String[] greetings = {
            "Hello! How can I assist you today?",
            "Hi there! What can I help you with?",
            "Greetings! How may I help you?",
            "Hey! I'm here to answer your programming questions.",
            "Welcome! Feel free to ask me anything about programming and technology."
        };
        return Utils.getRandomElement(greetings);
    }

    private String getRandomFarewell() {
        String[] farewells = {
            "Goodbye! Have a great day!",
            "See you later! Happy coding!",
            "Bye! Come back anytime you have questions.",
            "Take care! Keep learning and coding!",
            "Farewell! I hope I was helpful."
        };
        return Utils.getRandomElement(farewells);
    }

    private String getRandomUnknownResponse() {
        String[] unknownResponses = {
            "I'm sorry, I don't know the answer. Can you ask something related to programming?",
            "I'm not sure about that. Try asking about Java, Python, OOP, or other tech topics!",
            "I don't have information on that topic. I specialize in programming and technology.",
            "That's beyond my knowledge. I can help with Java, Python, data structures, algorithms, and more!",
            "I'm still learning! Could you ask me something about programming or computer science?"
        };
        return Utils.getRandomElement(unknownResponses);
    }

    private String getHelpText() {
        return "AVAILABLE COMMANDS:\n" +
               "----------------------------------------\n" +
               "1. Ask any question about programming, technology, etc.\n" +
               "2. Say hello, hi, good morning, etc. (Greetings)\n" +
               "3. Say bye, exit, quit, goodbye (Exit chatbot)\n" +
               "4. Type HELP to see this menu\n" +
               "5. Type JOKE to hear a programming joke\n" +
               "6. Type QUOTE or MOTIVATION for an inspiring quote\n" +
               "7. Type TIME to see current time\n" +
               "8. Type DATE to see today's date\n" +
               "9. Type TODAY or DAY to see current day\n" +
               "10. Type math expressions (e.g., 2+5, 12*3) for calculations\n" +
               "11. Type STATS to see session statistics\n" +
               "12. Type HISTORY to see conversation history\n" +
               "----------------------------------------\n" +
               "SAMPLE QUESTIONS:\n" +
               "- What is Java?\n" +
               "- What is OOP?\n" +
               "- Tell me about Python\n" +
               "- What is inheritance?\n" +
               "- Explain Machine Learning\n" +
               "- What is CodeAlpha?";
    }
}
