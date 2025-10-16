/*
You can adjust this logic as needed:

90% and above → Grade A

80% to <90% → Grade B

70% to <80% → Grade C

60% to <70% → Grade D

Below 60% → Grade F
*/
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Input number of subjects
        System.out.print("Enter the number of subjects: ");
        int subjects = scanner.nextInt();

        int[] marks = new int[subjects];
        int totalMarks = 0;

        // Step 2: Take marks input
        for (int i = 0; i < subjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];
        }

        // Step 3: Calculate Average Percentage
        double average = (double) totalMarks / subjects;

        // Step 4: Grade Calculation
        char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Step 5: Display Results
        System.out.println("\n----- Results -----");
        System.out.println("Total Marks: " + totalMarks + " out of " + (subjects * 100));
        System.out.println("Average Percentage: " + average + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
