# Student Grade Tracker

A console-based Java application built for the **CodeAlpha Internship Task-1**. This project demonstrates core Object-Oriented Programming concepts including classes, objects, encapsulation, ArrayList, exception handling, and more.

---

## Features

- **Add Student** – Register a new student with ID, name, department, and marks for 3 subjects.
- **View All Students** – Display all students in a formatted table.
- **Search Student** – Find a student by their unique ID and view full details.
- **Update Marks** – Modify marks for any student and automatically recalculate average & grade.
- **Delete Student** – Remove a student record by ID.
- **Statistics** – View highest/lowest/overall average, pass/fail count, topper name, and class ranking.
- **Generate Report** – Print a complete academic report with summary.
- **Sample Data** – Optionally load 6 pre-defined student records for quick testing.
- **Validation** – Duplicate ID prevention, marks range check (0–100), empty name check, and try-catch for invalid input.
- **Colored Console** – ANSI color codes for a modern terminal UI (Windows Terminal / VS Code / Linux / macOS).

---

## Technologies Used

| Technology | Purpose |
|------------|---------|
| **Java** | Core language (JDK 21+) |
| **OOP** | Classes, Objects, Encapsulation, Inheritance |
| **ArrayList** | Dynamic student storage |
| **Scanner** | User input handling |
| **Exception Handling** | try-catch for input validation |
| **Collections** | Sorting for rankings |

---

## Project Structure

```
StudentGradeTracker/
├── Student.java          # POJO class with attributes, grade calculation
├── StudentManager.java   # Business logic: CRUD, search, statistics, ranking
├── Main.java             # Entry point with colored menu UI
└── README.md             # Project documentation
```

---

## How to Run

### Prerequisites

- Java JDK 21 or later installed ([Download](https://jdk.java.net/))
- Verify installation:

```bash
java -version
javac -version
```

### Compile & Run

```bash
cd StudentGradeTracker

javac Student.java StudentManager.java Main.java

java Main
```

Or compile all at once:

```bash
javac *.java
java Main
```

---

## Sample Output

```
  ============================================
         STUDENT GRADE TRACKER v1.0
         CodeAlpha Internship Task-1
  ============================================

  Load sample data for testing? (y/n): y

  ✔ Sample data loaded: 6 students added.

  ============================================
                MAIN MENU
  ============================================

    1. Add Student
    2. View All Students
    3. Search Student
    4. Update Student Marks
    5. Delete Student
    6. Calculate Statistics
    7. Generate Report
    8. Exit

  --------------------------------------------
  Enter your choice  : 2

  ==================================================================================================
   ALL STUDENTS
  ==================================================================================================

  #    ID       Name                Department       Subject1   Subject2   Subject3   Average   Grade
  --------------------------------------------------------------------------------------------
   1.  S001     Alice Johnson       CSE                92.00      88.00      95.00      91.67   A+
   2.  S002     Bob Smith           ECE                78.00      81.00      74.00      77.67   B
   3.  S003     Charlie Brown       MECH               65.00      70.00      68.00      67.67   C
   4.  S004     Diana Prince        CSE                88.00      92.00      90.00      90.00   A
   5.  S005     Eve Davis           EEE                55.00      60.00      58.00      57.67   D
   6.  S006     Frank Miller        CIVIL              45.00      50.00      48.00      47.67   Fail
  --------------------------------------------------------------------------------------------

  Total Students: 6
```

---

## Grade Criteria

| Average Range | Grade |
|---------------|-------|
| >= 90         | A+    |
| >= 80         | A     |
| >= 70         | B     |
| >= 60         | C     |
| >= 50         | D     |
| < 50          | Fail  |

---

## Java Concepts Demonstrated

- **Classes & Objects** – `Student`, `StudentManager`, `Main`
- **Encapsulation** – Private fields with public getters/setters
- **ArrayList** – Dynamic student storage with `ArrayList<Student>`
- **Constructors** – Parameterized constructor with automatic average/grade calculation
- **Methods** – Modular design with reusable methods
- **Scanner** – Console input handling
- **Loops** – `while` for menu repetition, `for-each` for traversal
- **Switch-Case** – Menu-driven interface
- **If-Else** – Grade logic, validation
- **Exception Handling** – `try-catch` for `NumberFormatException`, `InputMismatchException`
- **Collections** – `Comparator` for ranking sort
- **String Formatting** – Table-aligned output with `String.format`

---

## Future Enhancements

- **File I/O** – Save/load student data from CSV or JSON files.
- **GUI Interface** – Build with JavaFX or Swing.
- **Database Integration** – Connect to MySQL or SQLite for persistence.
- **More Subjects** – Allow dynamic number of subjects.
- **Export Report** – Generate PDF or Excel report.
- **Authentication** – Admin login with password protection.
- **Search by Name** – Add ability to search by partial name match.

---

## Author

CodeAlpha Internship – Task 1  
Built with Java
