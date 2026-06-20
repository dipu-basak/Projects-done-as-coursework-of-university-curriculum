import javax.swing.*;
import java.awt.*;

/**
 * OnPanic - A productivity tool for students.
 * For focus and panic managOnement
 */
public class OnPanic extends JFrame {
    private JLabel timerLabel;
    private Timer timer;
    private int remainingSeconds = 25 * 60; // Default to 25 minutes
    private JPanel todoListPanel;
    private JTextField taskInput;

    public OnPanic() {
        setupUI();
    }

    private void setupUI() {
        // Basic window setup
        setTitle("OnPanic");
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(224, 229, 236));

        // 1. Smart Timer Panel
        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setBackground(new Color(224, 229, 236));
        timerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Digital Timer Display
        timerLabel = new JLabel("25:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 70));
        timerLabel.setForeground(new Color(50, 60, 80));
        timerPanel.add(timerLabel, BorderLayout.CENTER);

        // Timer Mode Buttons (Presets)
        JPanel modePanel = new JPanel(new FlowLayout());
        modePanel.setBackground(new Color(224, 229, 236));
        JButton btn25 = createStyledButton("25m Focus");
        JButton btn50 = createStyledButton("50m Deep Focus");
        JButton btn5 = createStyledButton("5m Break");

        // Action Listeners for mode switching
        btn25.addActionListener(e -> setTimer(25 * 60));
        btn50.addActionListener(e -> setTimer(50 * 60));
        btn5.addActionListener(e -> setTimer(5 * 60));

        modePanel.add(btn25);
        modePanel.add(btn50);
        modePanel.add(btn5);
        timerPanel.add(modePanel, BorderLayout.NORTH);

        // Control Buttons (Start/Pause)
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(new Color(224, 229, 236));
        JButton startBtn = createStyledButton("Start");
        JButton pauseBtn = createStyledButton("Pause");

        startBtn.addActionListener(e -> startTimer());
        pauseBtn.addActionListener(e -> { if(timer != null) timer.stop(); });

        controlPanel.add(startBtn);
        controlPanel.add(pauseBtn);
        timerPanel.add(controlPanel, BorderLayout.SOUTH);

        add(timerPanel, BorderLayout.NORTH);

        // 2. Study Task Checklist Panel
        JPanel todoPanel = new JPanel(new BorderLayout());
        todoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)), "Study Goals"));
        todoPanel.setBackground(Color.WHITE);

        // Task Input Area
        JPanel inputPanel = new JPanel(new BorderLayout());
        taskInput = new JTextField();
        taskInput.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JButton addTaskBtn = new JButton("Add Goal");

        // Add task via button click or pressing 'Enter'
        addTaskBtn.addActionListener(e -> addTask());
        taskInput.addActionListener(e -> addTask());

        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addTaskBtn, BorderLayout.EAST);
        todoPanel.add(inputPanel, BorderLayout.NORTH);

        // Checkboxes
        todoListPanel = new JPanel();
        todoListPanel.setLayout(new BoxLayout(todoListPanel, BoxLayout.Y_AXIS));
        todoListPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(todoListPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        todoPanel.add(scrollPane, BorderLayout.CENTER);

        add(todoPanel, BorderLayout.CENTER);
        // 3. About Button Panel
        JPanel aboutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        aboutPanel.setBackground(new Color(224, 229, 236));
        JButton aboutBtn = createStyledButton("About");
        aboutBtn.addActionListener(e -> showAbout());
        aboutPanel.add(aboutBtn);
        add(aboutPanel, BorderLayout.SOUTH);
        // default 25 minutes 
        setTimer(25 * 60);
    }

    // method to create  button 
    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        return btn;
    }

    // Method to set/reset the timer value
    private void setTimer(int seconds) {
        if (timer != null) timer.stop();
        remainingSeconds = seconds;
        updateTimerLabel();
    }

    // Method to start the countdown
    private void startTimer() {
        if (timer != null) timer.stop();
        timer = new Timer(1000, e -> {
            remainingSeconds--;
            updateTimerLabel();
            if (remainingSeconds <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(this,
                        "Time's up!",
                        "You did it!!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        timer.start();
    }

    // Update the UI label to reflect MM:SS format
    private void updateTimerLabel() {
        int m = remainingSeconds / 60;
        int s = remainingSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d", m, s));
    }

    // Method to add a new checkbox task to the list
    private void addTask() {
        String text = taskInput.getText().trim();
        if (!text.isEmpty()) {
            JCheckBox checkBox = new JCheckBox(text);
            checkBox.setBackground(Color.WHITE);
            checkBox.setFont(new Font("SansSerif", Font.PLAIN, 16));

            todoListPanel.add(checkBox);
            todoListPanel.revalidate(); // Refresh UI
            todoListPanel.repaint();
            taskInput.setText(""); // Clear input field
        }
    }

    // Method to show About dialog
    private void showAbout() {
        JOptionPane.showMessageDialog(this,
                "Developed by:\nMd Jannatun Naim\nID: 20242157010\nDipu Basak\nId: 20242079010",
                "About OnPanic",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Enable Antialiasing for smoother fonts
        // System.setProperty("awt.useSystemAAFontSettings","on");
        // System.setProperty("swing.aatext", "true");

        new OnPanic().setVisible(true);
    }
}
