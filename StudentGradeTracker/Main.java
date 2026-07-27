import java.util.Scanner;

public class Main {

    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String PURPLE = "\u001B[35m";
    private static final String BLUE = "\u001B[34m";
    private static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager(scanner);

        System.out.println(CYAN + BOLD);
        System.out.println("  ============================================");
        System.out.println("         STUDENT GRADE TRACKER v1.0");
        System.out.println("         CodeAlpha Internship Task-1");
        System.out.println("  ============================================" + RESET);

        System.out.print("\n  Load sample data for testing? (y/n): ");
        String loadSample = scanner.nextLine().trim();
        if (loadSample.equalsIgnoreCase("y")) {
            manager.loadSampleData();
        }

        while (true) {
            printMenu();
            System.out.print("  Enter your choice  : ");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(RED + "  \u26A0 Invalid choice. Please enter a number between 1 and 8." + RESET);
                continue;
            }

            switch (choice) {
                case 1:
                    manager.addStudent();
                    break;
                case 2:
                    manager.viewAllStudents();
                    break;
                case 3:
                    manager.searchStudent();
                    break;
                case 4:
                    manager.updateStudentMarks();
                    break;
                case 5:
                    manager.deleteStudent();
                    break;
                case 6:
                    manager.calculateStatistics();
                    break;
                case 7:
                    manager.generateReport();
                    break;
                case 8:
                    System.out.println(GREEN + "\n  " + "\u2713" + " Thank you for using Student Grade Tracker!" + RESET);
                    System.out.println(YELLOW + "  Exiting... Goodbye!\n" + RESET);
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println(RED + "  \u26A0 Invalid choice. Please enter a number between 1 and 8." + RESET);
            }

            if (choice >= 1 && choice <= 7) {
                System.out.print("\n  Press Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private static void printMenu() {
        System.out.println(BLUE + BOLD);
        System.out.println("  ============================================");
        System.out.println("                MAIN MENU");
        System.out.println("  ============================================" + RESET);
        System.out.println(CYAN);
        System.out.println("    1. Add Student");
        System.out.println("    2. View All Students");
        System.out.println("    3. Search Student");
        System.out.println("    4. Update Student Marks");
        System.out.println("    5. Delete Student");
        System.out.println("    6. Calculate Statistics");
        System.out.println("    7. Generate Report");
        System.out.println("    8. Exit");
        System.out.println(RESET);
        System.out.println("  --------------------------------------------");
    }
}
