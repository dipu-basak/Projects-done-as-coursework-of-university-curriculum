import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextArea display;
    private String operator = "";
    private double num1, num2, result;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextArea(3, 10); 
        display.setEditable(false);
        display.setFont(new Font("SansSerif", Font.BOLD, 22));
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10)); 
        panel.setBackground(new Color(240, 230, 150));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Added "." to the button labels
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", ".", "+",
                "=" 
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("SansSerif", Font.BOLD, 20));
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        JButton abtn = new JButton("About");
        abtn.addActionListener(this);
        add(abtn, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            // Logic for numbers and the decimal point
            if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
                // Prevent multiple decimals in one number
                if (command.equals(".") && display.getText().contains(".")) {
                    return; 
                }
                display.append(command);
            } 
            else if (command.equals("About")) {
                display.setText("Dipu Basak, ID: 20242079010 \nCourse Code: 2200\nSoftware Development Lab");
            } 
            else if (command.equals("C")) {
                display.setText("");
                num1 = num2 = result = 0;
                operator = "";
            } 
            else if (command.equals("=")) {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": 
                        if (num2 != 0) result = num1 / num2;
                        else { display.setText("Error: Div by 0"); return; }
                        break;
                }
                display.setText(String.valueOf(result));
                operator = ""; // Reset operator after calculation
            } 
            else {
                // Handle operators
                if (!display.getText().isEmpty()) {
                    num1 = Double.parseDouble(display.getText());
                    operator = command;
                    display.setText("");
                }
            }
        } catch (Exception ex) {
            display.setText("");
        }
    }

    public static void main(String[] args) {
        // try {
        //     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (Exception ignored) {}
        new Calculator();
    }
}