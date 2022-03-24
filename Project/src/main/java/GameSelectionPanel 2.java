import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameSelectionPanel {

    static JPanel mainPanel = new JPanel();
    static JButton overwatchButton;
    static JButton rocketLeagueButton;
    static JButton superSmashButton;
    static JButton leagueOfLegendsButton;
    static JButton chessButton;
    static String font = "Helvetica Neue";

    GameSelectionPanel() {

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame.setDefaultLookAndFeelDecorated(true);
        mainPanel.setLayout(null);

        // header
        JLabel largeTitle = new JLabel("OBS Esports Editor");
        largeTitle.setFont(new Font(font,Font.BOLD, 30));
        largeTitle.setBounds(50, 50, 300, 100);
        mainPanel.add(largeTitle);

        JSeparator separator = new JSeparator();
        separator.setBounds(50, 130, 1200, 1);
        Border borderSeparator = BorderFactory.createLineBorder(Color.BLACK, 5);
        separator.setBorder(borderSeparator);
        separator.setOrientation(SwingConstants.HORIZONTAL);
        mainPanel.add(separator);

        // contents

        // OW
        ImageIcon overwatchLogo = new ImageIcon(new ImageIcon((getClass().getResource("OverwatchLogo.png"))).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel overwatchLogoLabel = new JLabel(overwatchLogo);
        overwatchLogoLabel.setBounds(50, 295, 200, 200);
        mainPanel.add(overwatchLogoLabel);

        overwatchButton = new JButton("Overwatch");
        overwatchButton.setVerticalAlignment(SwingConstants.BOTTOM);
        overwatchButton.setFont(new Font(font, Font.PLAIN, 20));
        overwatchButton.setForeground(Color.darkGray);
        overwatchButton.setBounds(50, 300, 200, 200);
        overwatchButton.addActionListener(new GUI());
        mainPanel.add(overwatchButton);

        // RL

        ImageIcon rocketLeagueLogo = new ImageIcon(new ImageIcon((getClass().getResource("RocketLeagueLogo.png"))).getImage().getScaledInstance(120, 70, Image.SCALE_DEFAULT));
        JLabel rocketLeagueLogoLabel = new JLabel(rocketLeagueLogo);
        rocketLeagueLogoLabel.setBounds(300, 300, 200, 200);
        mainPanel.add(rocketLeagueLogoLabel);

        rocketLeagueButton = new JButton("Rocket League");
        rocketLeagueButton.setFont(new Font(font, Font.PLAIN, 20));
        rocketLeagueButton.setForeground(Color.darkGray);
        rocketLeagueButton.setVerticalAlignment(SwingConstants.BOTTOM);
        rocketLeagueButton.setBounds(300, 300, 200, 200);
        rocketLeagueButton.addActionListener(new GUI());
        mainPanel.add(rocketLeagueButton);

        // SSBU
        ImageIcon superSmashBrosLogo = new ImageIcon(new ImageIcon((getClass().getResource("SuperSmashBrosLogo.png"))).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel superSmashBrosLogoLabel = new JLabel(superSmashBrosLogo);
        superSmashBrosLogoLabel.setBounds(550, 300, 200, 200);
        mainPanel.add(superSmashBrosLogoLabel);

        superSmashButton = new JButton("Super Smash Bros");
        superSmashButton.setFont(new Font(font, Font.PLAIN, 20));
        superSmashButton.setForeground(Color.darkGray);
        superSmashButton.setVerticalAlignment(SwingConstants.BOTTOM);
        superSmashButton.setBounds(550, 300, 200, 200);
        superSmashButton.addActionListener(new GUI());
        mainPanel.add(superSmashButton);

        // LOL
        ImageIcon leagueOfLegendsLogo = new ImageIcon(new ImageIcon((getClass().getResource("LeagueOfLegendsLogo.png"))).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel leagueOfLegendsLogoLabel = new JLabel(leagueOfLegendsLogo);
        leagueOfLegendsLogoLabel.setBounds(800, 300, 200, 200);
        mainPanel.add(leagueOfLegendsLogoLabel);

        leagueOfLegendsButton = new JButton("<html><center>League of<br />Legends</html></center>");
        leagueOfLegendsButton.setFont(new Font(font, Font.PLAIN, 20));
        leagueOfLegendsButton.setForeground(Color.darkGray);
        leagueOfLegendsButton.setVerticalAlignment(SwingConstants.BOTTOM);
        leagueOfLegendsButton.setBounds(800, 300, 200, 200);
        leagueOfLegendsButton.addActionListener(new GUI());
        mainPanel.add(leagueOfLegendsButton);

        // CHESS
        ImageIcon chessLogo = new ImageIcon(new ImageIcon((getClass().getResource("ChessLogo.png"))).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel chessLogoLabel = new JLabel(chessLogo);
        chessLogoLabel.setBounds(1050, 300, 200, 200);
        mainPanel.add(chessLogoLabel);

        chessButton = new JButton("Chess");
        chessButton.setFont(new Font(font, Font.PLAIN, 20));
        chessButton.setForeground(Color.darkGray);
        chessButton.setVerticalAlignment(SwingConstants.BOTTOM);
        chessButton.setBounds(1050, 300, 200, 200);
        chessButton.addActionListener(new GUI());
        mainPanel.add(chessButton);
    }

    static JPanel retrievePanel() {
        return mainPanel;
    }

}
