package AIChatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils {

    private static final Random RANDOM = new Random();

    public static void printBanner() {
        System.out.println("========================================");
        System.out.println("         WELCOME TO AI CHATBOT");
        System.out.println("    CodeAlpha Java Programming Internship");
        System.out.println("========================================");
        System.out.println();
    }

    public static void printSeparator() {
        System.out.println("----------------------------------------");
    }

    public static void printHeader() {
        System.out.println("========================================");
        System.out.println("           AI CHATBOT");
        System.out.println("    CodeAlpha Java Programming Internship");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Type your question.");
        System.out.println("Type HELP for commands.");
        System.out.println("Type EXIT to quit.");
        System.out.println("----------------------------------------");
    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(formatter);
    }

    public static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return now.format(formatter);
    }

    public static String getCurrentDay() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
        return now.format(formatter);
    }

    public static int getRandomInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static <T> T getRandomElement(T[] array) {
        return array[RANDOM.nextInt(array.length)];
    }
}
