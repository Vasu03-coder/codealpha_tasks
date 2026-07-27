package AIChatbot;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot chatbot = new ChatBot();
        ConversationManager cm = chatbot.getConversationManager();

        Utils.printBanner();
        System.out.println("Session started at: " + cm.getSessionStartTime());
        Utils.printHeader();

        boolean running = true;

        while (running) {
            System.out.print("User : ");
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                System.out.println("Bot  : Please enter a valid input.");
                System.out.println("----------------------------------------");
                continue;
            }

            if (userInput.equalsIgnoreCase("STATS")) {
                cm.displaySessionStats();
                System.out.println("----------------------------------------");
                continue;
            }

            if (userInput.equalsIgnoreCase("HISTORY")) {
                displayHistory(cm);
                System.out.println("----------------------------------------");
                continue;
            }

            if (NLPProcessor.isFarewell(userInput)) {
                String response = chatbot.getResponse(userInput);
                System.out.println("Bot  : " + response);
                Utils.printSeparator();
                cm.displaySessionStats();
                System.out.println();
                System.out.println("Thank you for using AI Chatbot!");
                System.out.println("Session ended at: " + Utils.getCurrentDateTime());
                Utils.printSeparator();
                cm.saveHistory();
                running = false;
                continue;
            }

            String response = chatbot.getResponse(userInput);
            System.out.println("Bot  : " + response);
            System.out.println("----------------------------------------");
        }

        scanner.close();
    }

    private static void displayHistory(ConversationManager cm) {
        System.out.println("----------------------------------------");
        System.out.println("CONVERSATION HISTORY");
        System.out.println("----------------------------------------");
        for (String entry : cm.getChatHistory()) {
            System.out.println(entry);
        }
        System.out.println("----------------------------------------");
    }
}
