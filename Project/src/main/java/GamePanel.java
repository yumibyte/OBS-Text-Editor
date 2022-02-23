import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel {

    static JPanel mainPanel = new JPanel();
    static int xSelection = 50;
    static JLabel gameTitleLabel = new JLabel("");
    static JButton backButton = new JButton("Back");

    GamePanel() {

        mainPanel.setLayout(null);

        // header

        backButton.setBounds(10, 10, 100, 50);
        backButton.addActionListener(new GUI());
        mainPanel.add(backButton);

        JLabel largeTitle = new JLabel("OBS Esports Editor");
        largeTitle.setFont(new Font("Serif",Font.BOLD, 30));
        largeTitle.setBounds(40, 50, 300, 100);
        mainPanel.add(largeTitle);

        JSeparator separator = new JSeparator();
        separator.setBounds(50, 130, 1200, 1);
        Border borderSeparator = BorderFactory.createLineBorder(Color.BLACK, 5);
        separator.setBorder(borderSeparator);
        separator.setOrientation(SwingConstants.HORIZONTAL);
        mainPanel.add(separator);

        gameTitleLabel.setText(GUI.gameBaseFunctionality.gameType);
        gameTitleLabel.setFont(new Font("Serif",Font.BOLD, 30));
        gameTitleLabel.setForeground(Color.gray);
        gameTitleLabel.setBounds(900, 50, 300, 100);
        mainPanel.add(gameTitleLabel);

        // contents
        JLabel streamTitle = new JLabel("Stream Title");
        streamTitle.setFont(new Font("Serif",Font.BOLD, 30));
        streamTitle.setBounds(xSelection, 100, 300, 100);
        mainPanel.add(streamTitle);






    }

    public JPanel retrievePanel() {
        return mainPanel;
    }

}
