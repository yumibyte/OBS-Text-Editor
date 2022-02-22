import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameSelectionPanel {

    JPanel mainPanel = new JPanel();
    GameSelectionPanel() {
        mainPanel.setLayout(null);

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

        JButton overwatchButton = new JButton("Overwatch");
        overwatchButton.setBounds(50, 300, 200, 200);
        mainPanel.add(overwatchButton);

        JButton rocketLeagueButton = new JButton("Rocket League");
        rocketLeagueButton.setBounds(300, 300, 200, 200);
        mainPanel.add(rocketLeagueButton);

        JButton superSmashButton = new JButton("Rocket League");
        superSmashButton.setBounds(550, 300, 200, 200);
        mainPanel.add(superSmashButton);

        JButton leagueOfLegendsButton = new JButton("League of Legends");
        leagueOfLegendsButton.setBounds(800, 300, 200, 200);
        mainPanel.add(leagueOfLegendsButton);

        JButton chessButton = new JButton("Chess");
        chessButton.setBounds(1050, 300, 200, 200);
        mainPanel.add(chessButton);
    }

    JPanel retrievePanel() {
        return mainPanel;
    }

}
