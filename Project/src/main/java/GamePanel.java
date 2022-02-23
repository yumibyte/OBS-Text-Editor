import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GamePanel {

    static JPanel mainPanel = new JPanel();
    static int xSelection = 50;
    static String gameTitleType = "";
    static JLabel gameTitleLabel = new JLabel("");
    static JButton backButton = new JButton("Back");;
    static JTextField streamTitleTextField = new JTextField();
    static JLabel streamTitle = new JLabel("");

    GamePanel() {

        mainPanel.setLayout(null);

        // header
        backButton.setBounds(10, 10, 100, 50);
        if (backButton.getActionListeners().length == 0) {
            backButton.addActionListener(new GUI());

        }
        mainPanel.add(backButton);

        JLabel largeTitle = new JLabel("OBS Esports Editor");
        largeTitle.setFont(new Font("Serif",Font.BOLD, 30));
        largeTitle.setBounds(50, 50, 300, 100);
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

        // stream title
        streamTitle.setText(gameTitleType);
        streamTitle.setFont(new Font("Serif",Font.BOLD, 25));
        streamTitle.setForeground(Color.darkGray);
        streamTitle.setBounds(xSelection, 130, 300, 100);
        mainPanel.add(streamTitle);

        streamTitleTextField.setBounds(xSelection, 220, 500, 70);
        streamTitleTextField.addActionListener(new GUI());

        mainPanel.add(streamTitleTextField);


    }

    public JPanel retrievePanel() {
        return mainPanel;
    }

}
