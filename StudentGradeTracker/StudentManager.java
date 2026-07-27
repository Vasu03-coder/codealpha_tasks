import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManager {
    private ArrayList<Student> students;
    private Scanner scanner;

    public StudentManager(Scanner scanner) {
        this.students = new ArrayList<>();
        this.scanner = scanner;
    }

    public void addStudent() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   ADD NEW STUDENT");
        System.out.println("=".repeat(60));

        try {
            String id;
            while (true) {
                System.out.print("  Enter Student ID  : ");
                id = scanner.nextLine().trim();
                if (id.isEmpty()) {
                    System.out.println("  \u26A0 Student ID cannot be empty.");
                    continue;
                }
                if (findStudentById(id) != null) {
                    System.out.println("  \u26A0 Student ID already exists. Duplicate not allowed.");
                    continue;
                }
                break;
            }

            String name;
            while (true) {
                System.out.print("  Enter Name        : ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("  \u26A0 Name cannot be empty.");
                } else {
                    break;
                }
            }

            System.out.print("  Enter Department  : ");
            String dept = scanner.nextLine().trim();

            double s1 = readMarks("Subject 1");
            double s2 = readMarks("Subject 2");
            double s3 = readMarks("Subject 3");

            Student s = new Student(id, name, dept, s1, s2, s3);
            students.add(s);
            System.out.println("\n  " + "\u2713" + " Student added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("  \u26A0 Invalid input type. Please try again.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("  \u26A0 Error: " + e.getMessage());
        }
    }

    public void viewAllStudents() {
        System.out.println("\n" + "=".repeat(98));
        System.out.println("   ALL STUDENTS");
        System.out.println("=".repeat(98));

        if (students.isEmpty()) {
            System.out.println("\n  No students found.");
            return;
        }

        printTableHeader();
        System.out.println("  " + "-".repeat(92));
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.print("  " + padLeft(String.valueOf(i + 1), 3) + ".");
            s.displayStudent();
        }
        System.out.println("  " + "-".repeat(92));
        System.out.println("\n  Total Students: " + students.size());
    }

    public void searchStudent() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   SEARCH STUDENT");
        System.out.println("=".repeat(60));

        System.out.print("  Enter Student ID  : ");
        String id = scanner.nextLine().trim();

        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("\n  \u26A0 Student Not Found.");
            return;
        }

        displayStudentDetails(s);
    }

    public void updateStudentMarks() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   UPDATE STUDENT MARKS");
        System.out.println("=".repeat(60));

        System.out.print("  Enter Student ID  : ");
        String id = scanner.nextLine().trim();

        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("\n  \u26A0 Student Not Found.");
            return;
        }

        System.out.println("\n  Current Details:");
        displayStudentDetails(s);

        try {
            double s1 = readMarks("New Subject 1");
            double s2 = readMarks("New Subject 2");
            double s3 = readMarks("New Subject 3");

            s.setSubject1(s1);
            s.setSubject2(s2);
            s.setSubject3(s3);
            s.calculateAverage();
            s.calculateGrade();

            System.out.println("\n  " + "\u2713" + " Marks updated successfully!");
            System.out.println("\n  Updated Details:");
            displayStudentDetails(s);

        } catch (InputMismatchException e) {
            System.out.println("  \u26A0 Invalid input. Please enter numeric values.");
            scanner.nextLine();
        }
    }

    public void deleteStudent() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   DELETE STUDENT");
        System.out.println("=".repeat(60));

        System.out.print("  Enter Student ID  : ");
        String id = scanner.nextLine().trim();

        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("\n  \u26A0 Student Not Found.");
            return;
        }

        students.remove(s);
        System.out.println("\n  " + "\u2713" + " Student deleted successfully!");
    }

    public void calculateStatistics() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   STATISTICS REPORT");
        System.out.println("=".repeat(60));

        if (students.isEmpty()) {
            System.out.println("\n  No students to analyze.");
            return;
        }

        double highestAvg = Double.MIN_VALUE;
        double lowestAvg = Double.MAX_VALUE;
        double sumAvg = 0;
        Student topper = null;
        int passCount = 0;
        int failCount = 0;

        for (Student s : students) {
            double avg = s.getAverage();
            sumAvg += avg;

            if (avg > highestAvg) {
                highestAvg = avg;
                topper = s;
            }
            if (avg < lowestAvg) {
                lowestAvg = avg;
            }
            if ("Fail".equals(s.getGrade())) {
                failCount++;
            } else {
                passCount++;
            }
        }

        double overallAvg = sumAvg / students.size();

        System.out.println("\n  " + "-".repeat(40));
        System.out.println("  Number of Students  : " + students.size());
        System.out.println("  Highest Average     : " + String.format("%.2f", highestAvg));
        System.out.println("  Lowest Average      : " + String.format("%.2f", lowestAvg));
        System.out.println("  Overall Average     : " + String.format("%.2f", overallAvg));
        System.out.println("  Pass Count          : " + passCount);
        System.out.println("  Fail Count          : " + failCount);
        System.out.println("  " + "-".repeat(40));

        if (topper != null) {
            System.out.println("\n  \u2B50 TOPPER");
            System.out.println("  " + "-".repeat(40));
            System.out.println("  Name      : " + topper.getStudentName());
            System.out.println("  ID        : " + topper.getStudentId());
            System.out.println("  Dept      : " + topper.getDepartment());
            System.out.println("  Average   : " + String.format("%.2f", topper.getAverage()));
            System.out.println("  Grade     : " + topper.getGrade());
            System.out.println("  " + "-".repeat(40));
        }

        displayRanking();
    }

    private void displayRanking() {
        ArrayList<Student> sorted = new ArrayList<>(students);
        sorted.sort(Comparator.comparingDouble(Student::getAverage).reversed());

        System.out.println("\n  \uD83C\uDFC6 CLASS RANKING");
        System.out.println("  " + "=".repeat(40));
        System.out.println("  Rank  Name                 Avg    Grade");
        System.out.println("  " + "-".repeat(40));
        for (int i = 0; i < sorted.size(); i++) {
            Student s = sorted.get(i);
            System.out.println("  " + padLeft(String.valueOf(i + 1), 2)
                    + "     " + padRight(s.getStudentName(), 18)
                    + "  " + padLeft(String.format("%.2f", s.getAverage()), 6)
                    + "  " + s.getGrade());
        }
        System.out.println("  " + "-".repeat(40));
    }

    public void generateReport() {
        System.out.println("\n" + "=".repeat(98));
        System.out.println("   FINAL ACADEMIC REPORT");
        System.out.println("=".repeat(98));

        if (students.isEmpty()) {
            System.out.println("\n  No students in the system.");
            return;
        }

        printTableHeader();
        System.out.println("  " + "-".repeat(92));
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.print("  " + padLeft(String.valueOf(i + 1), 3) + ".");
            s.displayStudent();
        }
        System.out.println("  " + "-".repeat(92));

        System.out.println("\n  SUMMARY");
        System.out.println("  " + "-".repeat(40));
        int pass = 0, fail = 0;
        for (Student s : students) {
            if ("Fail".equals(s.getGrade())) fail++;
            else pass++;
        }
        System.out.println("  Total Students  : " + students.size());
        System.out.println("  Pass            : " + pass);
        System.out.println("  Fail            : " + fail);
        System.out.println("  Pass Percentage : " + String.format("%.2f%%", (double) pass / students.size() * 100));
        System.out.println("  " + "-".repeat(40));
    }

    private void printTableHeader() {
        System.out.println();
        System.out.println("  #    ID       Name                Department       Subject1   Subject2   Subject3   Average   Grade ");
    }

    private void displayStudentDetails(Student s) {
        System.out.println("\n  " + "-".repeat(40));
        System.out.println("  Student ID     : " + s.getStudentId());
        System.out.println("  Name           : " + s.getStudentName());
        System.out.println("  Department     : " + s.getDepartment());
        System.out.println("  Subject 1      : " + String.format("%.2f", s.getSubject1()));
        System.out.println("  Subject 2      : " + String.format("%.2f", s.getSubject2()));
        System.out.println("  Subject 3      : " + String.format("%.2f", s.getSubject3()));
        System.out.println("  Average        : " + String.format("%.2f", s.getAverage()));
        System.out.println("  Grade          : " + s.getGrade());
        System.out.println("  " + "-".repeat(40));
    }

    private Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    private double readMarks(String subjectName) {
        while (true) {
            try {
                System.out.print("  Enter " + subjectName + " Marks  : ");
                double marks = Double.parseDouble(scanner.nextLine().trim());
                if (marks < 0 || marks > 100) {
                    System.out.println("  \u26A0 Marks must be between 0 and 100.");
                } else {
                    return marks;
                }
            } catch (NumberFormatException e) {
                System.out.println("  \u26A0 Invalid input. Please enter a numeric value.");
            }
        }
    }

    public void loadSampleData() {
        students.add(new Student("S001", "Alice Johnson",   "CSE", 92, 88, 95));
        students.add(new Student("S002", "Bob Smith",       "ECE", 78, 81, 74));
        students.add(new Student("S003", "Charlie Brown",   "MECH", 65, 70, 68));
        students.add(new Student("S004", "Diana Prince",    "CSE", 88, 92, 90));
        students.add(new Student("S005", "Eve Davis",       "EEE", 55, 60, 58));
        students.add(new Student("S006", "Frank Miller",    "CIVIL", 45, 50, 48));
        System.out.println("\n  " + "\u2713" + " Sample data loaded: 6 students added.");
    }

    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    private String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}
