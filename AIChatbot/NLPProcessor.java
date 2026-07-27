package AIChatbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NLPProcessor {

    public static String cleanInput(String input) {
        if (input == null) return "";
        return input.trim().toLowerCase().replaceAll("[^a-zA-Z0-9+\\-*/\\s]", "").replaceAll("\\s+", " ").trim();
    }

    public static List<String> tokenize(String input) {
        String cleaned = cleanInput(input);
        if (cleaned.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(cleaned.split(" ")));
    }

    public static List<String> extractKeywords(String input) {
        List<String> tokens = tokenize(input);
        List<String> stopWords = Arrays.asList(
            "the", "a", "an", "is", "are", "was", "were", "be", "been",
            "being", "have", "has", "had", "do", "does", "did", "will",
            "would", "could", "should", "may", "might", "shall", "can",
            "to", "of", "in", "for", "on", "with", "at", "by", "from",
            "as", "into", "through", "during", "before", "after", "above",
            "below", "between", "out", "off", "over", "under", "again",
            "further", "then", "once", "here", "there", "when", "where",
            "why", "how", "all", "each", "every", "both", "few", "more",
            "most", "other", "some", "such", "no", "nor", "not", "only",
            "own", "same", "so", "than", "too", "very", "just", "about",
            "what", "which", "who", "whom", "this", "that", "these",
            "those", "am", "it", "its", "my", "your", "his", "her",
            "tell", "me", "about", "please"
        );
        List<String> keywords = new ArrayList<>();
        for (String token : tokens) {
            if (!stopWords.contains(token) && token.length() > 2) {
                keywords.add(token);
            }
        }
        return keywords;
    }

    public static boolean isGreeting(String input) {
        String cleaned = cleanInput(input);
        String[] greetings = {"hello", "hi", "good morning", "good afternoon", "good evening", "hey", "welcome", "howdy", "greetings"};
        for (String g : greetings) {
            if (cleaned.equals(g) || cleaned.startsWith(g + " ") || cleaned.contains(" " + g + " ")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFarewell(String input) {
        String cleaned = cleanInput(input);
        String[] farewells = {"bye", "exit", "quit", "goodbye", "see you", "see you later", "take care", "cya"};
        for (String f : farewells) {
            if (cleaned.equals(f) || cleaned.startsWith(f + " ") || cleaned.contains(" " + f + " ") || cleaned.equals("see you") || cleaned.equals("see you later")) {
                return true;
            }
        }
        if (cleaned.equals("bye") || cleaned.equals("exit") || cleaned.equals("quit") || cleaned.equals("goodbye")) {
            return true;
        }
        if (cleaned.startsWith("bye") || cleaned.startsWith("goodbye") || cleaned.startsWith("see you")) {
            return true;
        }
        return false;
    }

    public static boolean isMathExpression(String input) {
        String cleaned = cleanInput(input);
        return cleaned.matches(".*\\d+\\s*[+\\-*/]\\s*\\d+.*");
    }

    public static boolean isTimeRequest(String input) {
        String cleaned = cleanInput(input);
        return cleaned.contains("time");
    }

    public static boolean isDateRequest(String input) {
        String cleaned = cleanInput(input);
        return cleaned.contains("date");
    }

    public static boolean isDayRequest(String input) {
        String cleaned = cleanInput(input);
        return cleaned.contains("day") && (cleaned.contains("today") || cleaned.contains("current") || cleaned.contains("what"));
    }

    public static boolean isJokeRequest(String input) {
        String cleaned = cleanInput(input);
        return cleaned.contains("joke") || cleaned.contains("funny") || cleaned.contains("laugh") || cleaned.contains("humor");
    }

    public static boolean isQuoteRequest(String input) {
        String cleaned = cleanInput(input);
        return cleaned.contains("quote") || cleaned.contains("motivation") || cleaned.contains("inspire") || cleaned.contains("motivate");
    }

    public static boolean isHelpRequest(String input) {
        String cleaned = cleanInput(input);
        return cleaned.equals("help") || cleaned.equals("commands") || cleaned.equals("what can you do") || cleaned.equals("options");
    }

    public static String evaluateMath(String input) {
        try {
            String cleaned = cleanInput(input).replaceAll("[^\\d+\\-*/]", " ").trim().replaceAll("\\s+", "");
            for (char op : new char[]{'+', '-', '*', '/'}) {
                int idx = cleaned.lastIndexOf(op);
                if (idx > 0) {
                    double a = Double.parseDouble(cleaned.substring(0, idx));
                    double b = Double.parseDouble(cleaned.substring(idx + 1));
                    double result;
                    switch (op) {
                        case '+': result = a + b; break;
                        case '-': result = a - b; break;
                        case '*': result = a * b; break;
                        case '/':
                            if (b == 0) return "Division by zero is not allowed.";
                            result = a / b; break;
                        default: return null;
                    }
                    if (result == (long) result) {
                        return String.valueOf((long) result);
                    }
                    return String.valueOf(result);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
