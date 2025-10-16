import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// BankAccount Class
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            JOptionPane.showMessageDialog(null, "✅ Deposited ₹" + amount);
        } else {
            JOptionPane.showMessageDialog(null, " Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "✅ Withdrawn ₹" + amount);
        } else if (amount > balance) {
            JOptionPane.showMessageDialog(null, " Insufficient balance!");
        } else {
            JOptionPane.showMessageDialog(null, " Invalid withdrawal amount!");
        }
    }

    public double getBalance() {
        return balance;
    }
}

// ATM GUI Class
public class ATMGUI {
    private static final String PIN = "1234"; // Fixed PIN for login
    private BankAccount account;

    public ATMGUI(BankAccount account) {
        this.account = account;
        showLoginScreen();
    }

    // Login Screen
    private void showLoginScreen() {
        JFrame loginFrame = new JFrame("ATM Login");
        loginFrame.setSize(300, 150);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new FlowLayout());

        JLabel pinLabel = new JLabel("Enter PIN: ");
        JPasswordField pinField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");

        loginFrame.add(pinLabel);
        loginFrame.add(pinField);
        loginFrame.add(loginButton);

        loginButton.addActionListener(e -> {
            String enteredPIN = new String(pinField.getPassword());
            if (enteredPIN.equals(PIN)) {
                loginFrame.dispose();
                showATMMenu();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Incorrect PIN. Try again.");
            }
        });

        loginFrame.setVisible(true);
    }

    // ATM Menu Screen
    private void showATMMenu() {
        JFrame atmFrame = new JFrame("ATM Machine");
        atmFrame.setSize(400, 250);
        atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        atmFrame.setLayout(new GridLayout(4, 1, 10, 10));

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton exitButton = new JButton("Exit");

        atmFrame.add(withdrawButton);
        atmFrame.add(depositButton);
        atmFrame.add(checkBalanceButton);
        atmFrame.add(exitButton);

        // Button actions
        withdrawButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    account.withdraw(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });

        depositButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter amount to deposit:");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    account.deposit(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });

        checkBalanceButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(null, "💰 Current Balance: ₹" + account.getBalance())
        );

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "👋 Thank you for using the ATM!");
            atmFrame.dispose();
        });

        atmFrame.setVisible(true);
    }

    // Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMGUI(new BankAccount(5000.0)));
    }
}
