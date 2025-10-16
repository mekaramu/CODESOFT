import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;

        System.out.println("🎮 Welcome to the Number Guessing Game! 🎮");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            int attemptsLeft = 7; // Limit attempts
            boolean guessedCorrectly = false;

            System.out.println("\nI have chosen a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts to guess it!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println("🎉 Correct! You guessed the number.");
                    guessedCorrectly = true;
                    totalScore += 10; // Award points for correct guess
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("Too low! Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Out of attempts The number was: " + numberToGuess);
            }

            System.out.println("Your current score: " + totalScore);

            // Ask for replay
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nGame Over! Your final score: " + totalScore);
        scanner.close();
    }
}
