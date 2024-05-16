package com.example.pacman;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {

    public MainMenu() {
        // Set up the frame
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 360); // Updated screen size to 600x360
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());

        // Create a JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 360));

        // Add the GIF as the background
        JLabel background = new JLabel(new ImageIcon("Game/PacMan/src/main/resources/com/example/pacman/Game/Pacman/src/images/temp/ippoMain.gif")); // Change the path to your GIF file
        background.setBounds(0, 0, 600, 360); // Set bounds for the background label
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER); // Add background to the default layer

        // Add "LEADERBOARD" text
        JLabel leaderboardLabel = new JLabel("LEADERBOARD");
        leaderboardLabel.setBounds(16, 263, 100, 60); // Position and size of the label
        leaderboardLabel.setFont(new Font("Inter", Font.BOLD, 10)); // Set font
        leaderboardLabel.setForeground(new Color(0xFFD233)); // Set text color
        layeredPane.add(leaderboardLabel, JLayeredPane.PALETTE_LAYER); // Add leaderboard label to the palette layer

        // Load and resize the trophy image
        ImageIcon trophyIcon = new ImageIcon("Game/PacMan/src/images/main_menu/trophy.png"); // Path to the trophy image
        Image trophyImage = trophyIcon.getImage().getScaledInstance(43, 41, Image.SCALE_SMOOTH); // Resize image
        trophyIcon = new ImageIcon(trophyImage); // Create new ImageIcon with resized image
        JLabel trophyLabel = new JLabel(trophyIcon);
        trophyLabel.setBounds(32, 241, 43, 41); // Position and size of the trophy image
        layeredPane.add(trophyLabel, JLayeredPane.PALETTE_LAYER); // Add trophy image to the palette layer

        // Add "PLAY" text
        JLabel playLabel = new JLabel("PLAY");
        playLabel.setBounds(31, 65, 100, 20); // Position and size of the label
        playLabel.setFont(new Font("Inter", Font.BOLD, 12)); // Set font
        playLabel.setForeground(new Color(0x0FA958)); // Set text color
        layeredPane.add(playLabel, JLayeredPane.PALETTE_LAYER); // Add play label to the palette layer

        // Load and resize the play image
        ImageIcon playIcon = new ImageIcon("Game/PacMan/src/images/main_menu/play.png"); // Path to the play image
        Image playImage = playIcon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH); // Resize image
        playIcon = new ImageIcon(playImage); // Create new ImageIcon with resized image
        JLabel playImageLabel = new JLabel(playIcon);
        playImageLabel.setBounds(23, 17, 48, 48); // Position and size of the play image
        layeredPane.add(playImageLabel, JLayeredPane.PALETTE_LAYER); // Add play image to the palette layer

        // Add "SETTINGS" text
        JLabel settingsLabel = new JLabel("SETTINGS");
        settingsLabel.setBounds(510, 49, 100, 20); // Position and size of the label
        settingsLabel.setFont(new Font("Inter", Font.BOLD, 10)); // Set font
        settingsLabel.setForeground(Color.WHITE); // Set text color
        layeredPane.add(settingsLabel, JLayeredPane.PALETTE_LAYER); // Add settings label to the palette layer

        // Load and resize the gear image
        ImageIcon gearIcon = new ImageIcon("Game/PacMan/src/images/main_menu/gear.png"); // Path to the gear image
        Image gearImage = gearIcon.getImage().getScaledInstance(22, 23, Image.SCALE_SMOOTH); // Resize image
        gearIcon = new ImageIcon(gearImage); // Create new ImageIcon with resized image
        JLabel gearLabel = new JLabel(gearIcon);
        gearLabel.setBounds(524, 23, 22, 23); // Position and size of the gear image
        layeredPane.add(gearLabel, JLayeredPane.PALETTE_LAYER); // Add gear image to the palette layer

        // Add "QUIT" text
        JLabel quitLabel = new JLabel("QUIT");
        quitLabel.setBounds(524, 281, 100, 20); // Position and size of the label
        quitLabel.setFont(new Font("Inter", Font.BOLD, 12)); // Set font
        quitLabel.setForeground(new Color(0xF24E1E)); // Set text color
        layeredPane.add(quitLabel, JLayeredPane.PALETTE_LAYER); // Add quit label to the palette layer

        // Load and resize the quit image
        ImageIcon quitIcon = new ImageIcon("Game/PacMan/src/images/main_menu/quit.png"); // Path to the quit image
        Image quitImage = quitIcon.getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH); // Resize image
        quitIcon = new ImageIcon(quitImage); // Create new ImageIcon with resized image
        JLabel quitImageLabel = new JLabel(quitIcon);
        quitImageLabel.setBounds(518, 239, 42, 42); // Position and size of the quit image
        layeredPane.add(quitImageLabel, JLayeredPane.PALETTE_LAYER); // Add quit image to the palette layer

        // Create the settings panel
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBounds(131, 20, 338, 284);
        settingsPanel.setBackground(new Color(217, 217, 217, 204)); // Background color with opacity
        settingsPanel.setBorder(new RoundedBorder(16)); // Set rounded border
        settingsPanel.setVisible(false); // Initially hidden
        layeredPane.add(settingsPanel, JLayeredPane.MODAL_LAYER); // Add settings panel to the modal layer

        // Add mouse listener to gear icon to show/hide settings panel
        gearLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settingsPanel.setVisible(!settingsPanel.isVisible());
            }
        });

        // Add the layeredPane to the frame
        add(layeredPane, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}

// Custom border class to create rounded borders
class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
