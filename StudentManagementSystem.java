import java.io.*;
import java.util.*;

// Student Class
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters & Setters
    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }
}

// Student Management System
class StudentManagementSystem {
    private List<Student> students;
    private static final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Add student
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    // Remove student by roll number
    public boolean removeStudent(int rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber() == rollNumber) {
                iterator.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // Search student
    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null;
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Save student data to file
    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load student data from file
    private void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) in.readObject();
        } catch (FileNotFoundException e) {
            students = new ArrayList<>(); // no file yet
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            students = new ArrayList<>();
        }
    }
}

// Main Class - Console UI
public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: // Add
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int roll = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();

                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Name and Grade cannot be empty!");
                    } else {
                        sms.addStudent(new Student(name, roll, grade));
                        System.out.println("✅ Student added successfully!");
                    }
                    break;

                case 2: // Remove
                    System.out.print("Enter Roll Number to remove: ");
                    int removeRoll = scanner.nextInt();
                    if (sms.removeStudent(removeRoll)) {
                        System.out.println("✅ Student removed.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3: // Search
                    System.out.print("Enter Roll Number to search: ");
                    int searchRoll = scanner.nextInt();
                    Student found = sms.searchStudent(searchRoll);
                    if (found != null) {
                        System.out.println("✅ Found: " + found);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4: // Display All
                    sms.displayAllStudents();
                    break;

                case 5: // Exit
                    System.out.println("👋 Exiting Student Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

