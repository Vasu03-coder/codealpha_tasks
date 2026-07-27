package AIChatbot;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ConversationManager {

    private final List<String> chatHistory;
    private int questionCount;
    private int greetingCount;
    private int unknownCount;
    private int responseCount;
    private final LocalDateTime sessionStart;

    public ConversationManager() {
        chatHistory = new ArrayList<>();
        questionCount = 0;
        greetingCount = 0;
        unknownCount = 0;
        responseCount = 0;
        sessionStart = LocalDateTime.now();
    }

    public void addUserMessage(String message) {
        String entry = "[USER] " + Utils.getCurrentDateTime() + " - " + message;
        chatHistory.add(entry);
        FileManager.appendToHistory(entry);
    }

    public void addBotResponse(String response) {
        String entry = "[BOT] " + Utils.getCurrentDateTime() + " - " + response;
        chatHistory.add(entry);
        FileManager.appendToHistory(entry);
        responseCount++;
    }

    public void incrementQuestionCount() {
        questionCount++;
    }

    public void incrementGreetingCount() {
        greetingCount++;
    }

    public void incrementUnknownCount() {
        unknownCount++;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public int getGreetingCount() {
        return greetingCount;
    }

    public int getUnknownCount() {
        return unknownCount;
    }

    public int getResponseCount() {
        return responseCount;
    }

    public List<String> getChatHistory() {
        return new ArrayList<>(chatHistory);
    }

    public void displaySessionStats() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(sessionStart, now);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        System.out.println();
        Utils.printSeparator();
        System.out.println("SESSION STATISTICS");
        Utils.printSeparator();
        System.out.println("Questions Asked    : " + questionCount);
        System.out.println("Greetings Exchanged: " + greetingCount);
        System.out.println("Unknown Questions  : " + unknownCount);
        System.out.println("Total Responses    : " + responseCount);
        System.out.println("Session Duration   : " + hours + "h " + minutes + "m " + seconds + "s");
        Utils.printSeparator();
    }

    public void saveHistory() {
        FileManager.saveChatHistory(chatHistory);
    }

    public String getSessionStartTime() {
        return sessionStart.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
