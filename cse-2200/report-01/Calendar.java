import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
//import java.time.LocalDate;
import java.time.YearMonth;


public class Calendar extends JFrame {

    // Theme colors and settings
    private final Color BG_DARK = new Color(245, 245, 245);
    private final Color ACCENT_GREEN = new Color(0, 255, 127);
    private final Color ACCENT_PINK = new Color(255, 105, 180);
    private final int BORDER_SIZE = 4;

    private YearMonth currentMonth; 
    private JPanel daysPanel;
    private JLabel monthYearHeader;
    private JTextField yearInput, monthInput;

    public Calendar() {
        // CUrrent month and year
        currentMonth = YearMonth.now(); 
        
        setupFrame();
        
        // Input Section 
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(BG_DARK);
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        yearInput = createTextField(String.valueOf(currentMonth.getYear()), 60);
        monthInput = createTextField(String.valueOf(currentMonth.getMonthValue()), 40);
        JButton goBtn = createButton("GO", ACCENT_GREEN);
        
        goBtn.addActionListener(e -> {
            try {
                int y = Integer.parseInt(yearInput.getText());
                int m = Integer.parseInt(monthInput.getText());
                if (m < 1 || m > 12) throw new Exception();
                currentMonth = YearMonth.of(y, m);
                refreshCalendar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "INVALID DATE");
            }
        });

        inputPanel.add(new JLabel("Y:") {{ setForeground(Color.BLACK); }});
        inputPanel.add(yearInput);
        inputPanel.add(new JLabel("M:") {{ setForeground(Color.BLACK); }});
        inputPanel.add(monthInput);
        inputPanel.add(goBtn);

        // Header and Grid Section (Middle)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(BG_DARK);

        monthYearHeader = createStyledLabel(currentMonth.toString(), 24, ACCENT_GREEN);
        centerPanel.add(monthYearHeader, BorderLayout.NORTH);

        daysPanel = new JPanel(new GridLayout(0, 7, 5, 5));
        daysPanel.setBackground(BG_DARK);
        daysPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        centerPanel.add(daysPanel, BorderLayout.CENTER);

        // Footer Section (Bottom)
        JPanel footer = new JPanel(new GridLayout(1, 3, 5, 5));
        footer.setBackground(BG_DARK);
        footer.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton prevBtn = createButton("<", Color.LIGHT_GRAY);
        prevBtn.addActionListener(e -> updateMonth(-1));

        JButton nextBtn = createButton(">", Color.LIGHT_GRAY);
        nextBtn.addActionListener(e -> updateMonth(1));

        JButton aboutBtn = createButton("ABOUT", ACCENT_PINK);
        aboutBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "DEVELOPED BY: Dipu Basak\nID: 20242079020\nSection: c\nCourse Code: 2201"));

        footer.add(prevBtn);
        footer.add(nextBtn);
        footer.add(aboutBtn);

        // Add everything to frame
        add(inputPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        refreshCalendar();
        setLocationRelativeTo(null);
    }

    private void setupFrame() {
        setTitle("CALENDAR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 650);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);
    }

    private void updateMonth(int increment) {
        currentMonth = currentMonth.plusMonths(increment);
        // Sync input fields with navigation
        yearInput.setText(String.valueOf(currentMonth.getYear()));
        monthInput.setText(String.valueOf(currentMonth.getMonthValue()));
        refreshCalendar();
    }

private void refreshCalendar() {
    daysPanel.removeAll();

    // Update header
    monthYearHeader.setText(currentMonth.getMonth().name() + " " + currentMonth.getYear());

    // Weekday headers
    String[] days = {"S","M","T","W","T","F","S"};
    for (String d : days) {
        JLabel lbl = new JLabel(d, SwingConstants.CENTER);
        lbl.setForeground(Color.BLACK);
        daysPanel.add(lbl);
    }

    // emptyCells calculation
    int emptyCells = currentMonth.atDay(1).getDayOfWeek().getValue() % 7;
    for (int i = 0; i < emptyCells; i++) daysPanel.add(new JLabel(""));

    // Days of month
    for (int d = 1; d <= currentMonth.lengthOfMonth(); d++) {
        daysPanel.add(createStyledLabel(String.valueOf(d), 16, Color.WHITE));
    }

    daysPanel.revalidate();
    daysPanel.repaint();
}
    private JLabel createStyledLabel(String text, int size, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(2, 2, getWidth(), getHeight());
                g.setColor(color);
                g.fillRect(0, 0, getWidth() - 2, getHeight() - 2);
                g.setColor(Color.BLACK);
                ((Graphics2D) g).setStroke(new BasicStroke(BORDER_SIZE));
                g.drawRect(0, 0, getWidth() - 2, getHeight() - 2);
                super.paintComponent(g);
            }
        };
        label.setFont(new Font("Arial Black", Font.BOLD, size));
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(40, 40));
        return label;
    }

    private JTextField createTextField(String text, int width) {
        JTextField tf = new JTextField(text);
        tf.setPreferredSize(new Dimension(width, 30));
        tf.setBackground(Color.WHITE);
        tf.setBorder(new LineBorder(Color.BLACK, 2));
        tf.setHorizontalAlignment(JTextField.CENTER);
        return tf;
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial Black", Font.BOLD, 12));
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(Color.BLACK, 2));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calendar().setVisible(true));
    }
}