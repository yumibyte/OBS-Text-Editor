import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameSelectionPanel {

    static JPanel mainPanel = new JPanel();
    static JButton overwatchButton = new JButton("Overwatch");
    static JButton rocketLeagueButton = new JButton("Rocket League");
    static JButton superSmashButton = new JButton("Rocket League");
    static JButton leagueOfLegendsButton = new JButton("League of Legends");
    static JButton chessButton = new JButton("Chess");

    GameSelectionPanel() {
        mainPanel.setLayout(null);

        // header
        JLabel largeTitle = new JLabel("OBS Esports Editor");
        largeTitle.setFont(new Font("Serif",Font.BOLD, 30));
        largeTitle.setBounds(60, 20, 300, 100);
        mainPanel.add(largeTitle);

        JSeparator separator = new JSeparator();
        separator.setBounds(50, 100, 1200, 1);
        Border borderSeparator = BorderFactory.createLineBorder(Color.BLACK, 5);
        separator.setBorder(borderSeparator);
        separator.setOrientation(SwingConstants.HORIZONTAL);
        mainPanel.add(separator);

        //co

        overwatchButton.setBounds(50, 300, 200, 200);
        overwatchButton.addActionListener(new GUI());
        mainPanel.add(overwatchButton);

        rocketLeagueButton.setBounds(300, 300, 200, 200);
        rocketLeagueButton.addActionListener(new GUI());
        mainPanel.add(rocketLeagueButton);

        superSmashButton.setBounds(550, 300, 200, 200);
        superSmashButton.addActionListener(new GUI());
        mainPanel.add(superSmashButton);

        leagueOfLegendsButton.setBounds(800, 300, 200, 200);
        leagueOfLegendsButton.addActionListener(new GUI());
        mainPanel.add(leagueOfLegendsButton);

        chessButton.setBounds(1050, 300, 200, 200);
        chessButton.addActionListener(new GUI());
        mainPanel.add(chessButton);
    }

    JPanel retrievePanel() {
        return mainPanel;
    }

}
