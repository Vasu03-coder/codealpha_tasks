import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Utility class providing shared console formatting helpers,
 * safe input reading and validation methods used across the application.
 */
public final class Utils {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static final String LINE = "========================================";
    public static final String THIN_LINE = "----------------------------------------";

    private Utils() {
    }

    /* ---------------------- Console formatting ---------------------- */

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printThinLine() {
        System.out.println(THIN_LINE);
    }

    public static void printCentered(String text) {
        int width = LINE.length();
        if (text.length() >= width) {
            System.out.println(text);
            return;
        }
        int leftPadding = (width - text.length()) / 2;
        System.out.println(String.format("%" + leftPadding + "s%s", "", text));
    }

    public static void printHeader(String title) {
        printLine();
        printCentered(title);
        printLine();
    }

    /* ---------------------- Input reading ---------------------- */

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    /* ---------------------- Validation ---------------------- */

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean isValidAge(int age) {
        return age >= 1 && age <= 120;
    }

    public static LocalDate parseDate(String input) {
        input = input.trim();
        try {
            return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e2) {
                throw new DateTimeParseException(
                        "Invalid date format. Use yyyy-MM-dd or dd/MM/yyyy.", input, 0);
            }
        }
    }

    public static LocalDate readDate(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return parseDate(input);
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to);
    }

    /* ---------------------- Formatting helpers ---------------------- */

    public static String formatCurrency(double amount) {
        return String.format("Rs %.2f", amount);
    }
}
