package Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.security.SecureRandom;

public class PasswordGenerator extends JFrame {

    private JTextField passwordField;
    private JSpinner lengthSpinner;
    private JCheckBox upperCaseBox, lowerCaseBox, numberBox, symbolBox;

    public PasswordGenerator() {
        setTitle("✨ Password Generator ✨");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(34, 40, 49));

        JLabel title = new JLabel(" Password Generator ");
        title.setFont(new Font("Verdana", Font.BOLD, 20));
        title.setForeground(new Color(255, 203, 0));
        title.setBounds(90, 10, 320, 30);
        panel.add(title);

        JLabel lengthLabel = new JLabel("Password Length:");
        lengthLabel.setForeground(Color.CYAN);
        lengthLabel.setBounds(50, 60, 150, 25);
        panel.add(lengthLabel);

        lengthSpinner = new JSpinner(new SpinnerNumberModel(12, 4, 32, 1));
        lengthSpinner.setBounds(180, 60, 60, 25);
        panel.add(lengthSpinner);

        upperCaseBox = new JCheckBox("Include Uppercase (A-Z)");
        upperCaseBox.setBounds(50, 100, 200, 25);
        upperCaseBox.setForeground(Color.WHITE);
        upperCaseBox.setBackground(panel.getBackground());
        panel.add(upperCaseBox);

        lowerCaseBox = new JCheckBox("Include Lowercase (a-z)");
        lowerCaseBox.setBounds(270, 100, 200, 25);
        lowerCaseBox.setForeground(Color.WHITE);
        lowerCaseBox.setBackground(panel.getBackground());
        panel.add(lowerCaseBox);

        numberBox = new JCheckBox("Include Numbers (0-9)");
        numberBox.setBounds(50, 130, 200, 25);
        numberBox.setForeground(Color.WHITE);
        numberBox.setBackground(panel.getBackground());
        panel.add(numberBox);

        symbolBox = new JCheckBox("Include Symbols (@#$%)");
        symbolBox.setBounds(270, 130, 200, 25);
        symbolBox.setForeground(Color.WHITE);
        symbolBox.setBackground(panel.getBackground());
        panel.add(symbolBox);

        JButton generateButton = new JButton("🎲 Generate Password");
        generateButton.setBounds(140, 170, 200, 35);
        generateButton.setBackground(new Color(57, 255, 20));
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(generateButton);

        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setBounds(50, 220, 380, 30);
        passwordField.setFont(new Font("Courier New", Font.BOLD, 16));
        passwordField.setForeground(Color.MAGENTA);
        panel.add(passwordField);

        JButton copyButton = new JButton("📋 Copy");
        copyButton.setBounds(190, 260, 100, 30);
        copyButton.setBackground(new Color(255, 105, 180));
        copyButton.setForeground(Color.BLACK);
        panel.add(copyButton);

        generateButton.addActionListener(e -> generatePassword());
        copyButton.addActionListener(e -> copyToClipboard());

        add(panel);
        setVisible(true);
    }

    private void generatePassword() {
        int length = (Integer) lengthSpinner.getValue();
        boolean useUpper = upperCaseBox.isSelected();
        boolean useLower = lowerCaseBox.isSelected();
        boolean useNumbers = numberBox.isSelected();
        boolean useSymbols = symbolBox.isSelected();

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()-_+=<>?";

        StringBuilder charPool = new StringBuilder();
        if (useUpper) charPool.append(upper);
        if (useLower) charPool.append(lower);
        if (useNumbers) charPool.append(numbers);
        if (useSymbols) charPool.append(symbols);

        if (charPool.length() == 0) {
            passwordField.setText("⚠️ Select at least one option!");
            return;
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        passwordField.setText(password.toString());
    }

    private void copyToClipboard() {
        String password = passwordField.getText();
        if (!password.isEmpty()) {
            StringSelection selection = new StringSelection(password);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
            JOptionPane.showMessageDialog(this, "Password copied to clipboard!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordGenerator());
    }
}
