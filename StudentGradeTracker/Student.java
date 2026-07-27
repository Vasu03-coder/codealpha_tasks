import java.text.DecimalFormat;

public class Student {
    private String studentId;
    private String studentName;
    private String department;
    private double subject1;
    private double subject2;
    private double subject3;
    private double average;
    private String grade;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Student(String studentId, String studentName, String department,
                   double subject1, double subject2, double subject3) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        calculateAverage();
        calculateGrade();
    }

    public void calculateAverage() {
        this.average = (subject1 + subject2 + subject3) / 3.0;
    }

    public void calculateGrade() {
        if (average >= 90) {
            this.grade = "A+";
        } else if (average >= 80) {
            this.grade = "A";
        } else if (average >= 70) {
            this.grade = "B";
        } else if (average >= 60) {
            this.grade = "C";
        } else if (average >= 50) {
            this.grade = "D";
        } else {
            this.grade = "Fail";
        }
    }

    public void displayStudent() {
        System.out.println("  " + padRight(studentId, 8)
                + "  " + padRight(studentName, 18)
                + "  " + padRight(department, 14)
                + "  " + padLeft(df.format(subject1), 8)
                + "  " + padLeft(df.format(subject2), 8)
                + "  " + padLeft(df.format(subject3), 8)
                + "  " + padLeft(df.format(average), 8)
                + "  " + padRight(grade, 6));
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getSubject1() { return subject1; }
    public void setSubject1(double subject1) { this.subject1 = subject1; }

    public double getSubject2() { return subject2; }
    public void setSubject2(double subject2) { this.subject2 = subject2; }

    public double getSubject3() { return subject3; }
    public void setSubject3(double subject3) { this.subject3 = subject3; }

    public double getAverage() { return average; }
    public String getGrade() { return grade; }

    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    private String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}
