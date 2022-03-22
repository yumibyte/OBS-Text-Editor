import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class GamePanel {

    static JPanel mainPanel = new JPanel();
    static int xSelection = 50;
    static String gameTitleType = "";
    static JLabel gameTitleLabel = new JLabel("");
    static JButton backButton = new JButton("Back");;
    static JTextField streamTitleTextField = new JTextField();
    static JLabel streamTitle = new JLabel("");

    static String[] superSmashPlayerOptions = {"Matthew Chance Lopez","Mason Harder","Jordan Hopper","Noah","Gavin Phillian"};
    static JComboBox playerDropdown;
    static JButton generateStreamButton = new JButton("Generate Stream");

    static JTextArea streamPreviewLabel = new JTextArea("");
    static JLabel streamPreviewPlayerSelectedLabel = new JLabel("");

    // stream preview roster
    static ArrayList<String> selectedPlayers = new ArrayList<String>();
    static String title = "";

    GamePanel() {

        mainPanel.setLayout(null);

        // header
        backButton.setBounds(10, 10, 100, 50);

        backButton.addActionListener(new GUI());
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

        gameTitleLabel.setFont(new Font("Serif",Font.BOLD, 30));
        gameTitleLabel.setForeground(Color.gray);
        gameTitleLabel.setBounds(900, 50, 300, 100);
        mainPanel.add(gameTitleLabel);

        // CONTENTS

        // stream title
        streamTitle.setText(gameTitleType);
        streamTitle.setFont(new Font("Serif",Font.BOLD, 25));
        streamTitle.setForeground(Color.darkGray);
        streamTitle.setBounds(xSelection, 130, 300, 100);
        mainPanel.add(streamTitle);

        streamTitleTextField.setBounds(xSelection, 220, 500, 70);
        streamTitleTextField.addActionListener(new GUI());
        mainPanel.add(streamTitleTextField);

        // Roster selection
        JLabel rosterSelectionLabel = new JLabel("Roster Selection");
        rosterSelectionLabel.setFont(new Font("Serif",Font.BOLD, 25));
        rosterSelectionLabel.setForeground(Color.darkGray);
        rosterSelectionLabel.setBounds(xSelection, 280, 300, 100);
        mainPanel.add(rosterSelectionLabel);

        int numberOfRosterSlots = 1;

        // determine number of roster slots
        switch (gameTitleType) {
            case "Overwatch":
                numberOfRosterSlots = 6;
                break;
            case "Rocket League":
                numberOfRosterSlots = 8;
                break;
            case "League of Legends":
                numberOfRosterSlots = 5;
                break;
        }

        playerDropdown = new JComboBox(superSmashPlayerOptions);
        playerDropdown.setBounds(xSelection, 340, 300, 100);
        playerDropdown.addActionListener(new GUI());
        mainPanel.add(playerDropdown);

//        player1Dropdown.setBounds(xSelection, 340, 300, 100);
//        player1Dropdown.addActionListener(new GUI());
//        mainPanel.add(player1Dropdown);

        // generate stream
        generateStreamButton.setFont(new Font("Serif",Font.BOLD, 25));
        generateStreamButton.setBackground(Color.blue);
        generateStreamButton.setForeground(Color.white);
        generateStreamButton.setOpaque(true);
        generateStreamButton.setBounds(500, 500, 300, 100);
        generateStreamButton.addActionListener(new GUI());
        mainPanel.add(generateStreamButton);

        // PREVIEW PANEL
        JLabel streamPreviewTitle = new JLabel("Stream Preview");
        streamPreviewTitle.setFont(new Font("Serif",Font.BOLD, 25));
        streamPreviewTitle.setForeground(Color.gray);
        streamPreviewTitle.setBounds(700, 130, 300, 100);
        mainPanel.add(streamPreviewTitle);

        streamPreviewLabel.setFont(new Font("Serif",Font.PLAIN, 15));
        streamPreviewLabel.setForeground(Color.gray);
        streamPreviewLabel.setBounds(700, 220, 500, 100);
        mainPanel.add(streamPreviewLabel);

        streamPreviewPlayerSelectedLabel.setFont(new Font("Serif",Font.PLAIN, 15));
        streamPreviewPlayerSelectedLabel.setForeground(Color.gray);
        streamPreviewPlayerSelectedLabel.setBounds(700, 200, 500, 100);
        mainPanel.add(streamPreviewPlayerSelectedLabel);

        JSeparator separatorPreview = new JSeparator();
        separatorPreview.setBounds(650, 150, 1, 300);
        Border borderSeparatorPreview = BorderFactory.createLineBorder(Color.BLACK, 5);
        separatorPreview.setBorder(borderSeparatorPreview);
        separatorPreview.setOrientation(SwingConstants.VERTICAL);
        mainPanel.add(separatorPreview);

    }

    public JPanel retrievePanel() {
        return mainPanel;
    }

    public void updateStreamPreview() {

        String streamPreviewString = "";

        streamPreviewString += title + "\n";

        for (String player: selectedPlayers) {
            streamPreviewString += player + "\n";
        }


        System.out.println(streamPreviewString);
        streamPreviewLabel.setText(streamPreviewString);


    }


}
