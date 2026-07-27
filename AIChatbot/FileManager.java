package AIChatbot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_NAME = "chat_history.txt";

    public static void saveChatHistory(List<String> history) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String line : history) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving chat history: " + e.getMessage());
        }
    }

    public static List<String> loadChatHistory() {
        List<String> history = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return history;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading chat history: " + e.getMessage());
        }
        return history;
    }

    public static void appendToHistory(String entry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to chat history: " + e.getMessage());
        }
    }
}
