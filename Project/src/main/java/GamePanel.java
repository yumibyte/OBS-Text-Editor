import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.InputStream;
import java.util.ArrayList;

import static java.lang.System.in;

public class GamePanel {

    static JPanel mainPanel = new JPanel();
    static int xSelection = 50;

    // title/headers
    static String gameTitleType = "";
    static JLabel gameTitleLabel = new JLabel("");
    static JButton backButton = new JButton("Back");;
    static JTextField streamTitleTextField = new JTextField();
    static JLabel streamTitle = new JLabel("");

    // roster
    static JComboBox playerDropdown;
    static JComboBox playerTypeDropdown;
    static JButton clearRosterButton;
    static JButton filterButton;
    static ImageIcon filterIcon;
    static JLabel rosterSelectionPlayerTypeLabel;

    // stream preview
    static JTextArea streamPreviewLabel = new JTextArea("");
    static JLabel streamPreviewPlayerSelectedLabel = new JLabel("");
    // stream preview roster
    static ArrayList<String> selectedPlayers = new ArrayList<String>();
    static ArrayList<String> selectedPlayerTypes = new ArrayList<String>();
    static String title = "";

    // generate stream
    static JButton generateStreamButton = new JButton("Generate Stream");

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

        // top right label
        gameTitleLabel.setFont(new Font("Serif",Font.BOLD, 30));
        gameTitleLabel.setForeground(Color.gray);
        gameTitleLabel.setBounds(900, 50, 300, 100);
        mainPanel.add(gameTitleLabel);

        // CONTENTS
        // topmost label above title input
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

        // roster selection
        int yRosterLabel = 405;
        int yRosterLabelDropdown = 470;
        int yFilter = 435;
        int yClearRoster = 515;

        // OW and LoL require playerTypes
        rosterSelectionPlayerTypeLabel = new JLabel("Player type");
        rosterSelectionPlayerTypeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        rosterSelectionPlayerTypeLabel.setForeground(Color.darkGray);
        rosterSelectionPlayerTypeLabel.setBounds(xSelection + 300, 335, 300, 100);
        mainPanel.add(rosterSelectionPlayerTypeLabel);

        playerTypeDropdown = new JComboBox(GUI.gameBaseFunctionality.availablePlayerTypes.toArray());
        playerTypeDropdown.setBounds(xSelection + 300, 400, 300, 40);
        playerTypeDropdown.addActionListener(new GUI());
        mainPanel.add(playerTypeDropdown);

        JLabel rosterSelectionPlayerNameLabel = new JLabel("Player name");
        rosterSelectionPlayerNameLabel.setFont(new Font("Serif",Font.PLAIN, 15));
        rosterSelectionPlayerNameLabel.setForeground(Color.darkGray);
        rosterSelectionPlayerNameLabel.setBounds(xSelection + 5, 335, 300, 100);
        mainPanel.add(rosterSelectionPlayerNameLabel);

        playerDropdown = new JComboBox(GUI.gameBaseFunctionality.availablePlayers.toArray());
        playerDropdown.setBounds(xSelection - 5, 400, 300, 40);
        playerDropdown.addActionListener(new GUI());
        mainPanel.add(playerDropdown);

        filterIcon = new ImageIcon(new ImageIcon((getClass().getResource("filterSymbol.png"))).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
//        filterIcon = filterIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
//        ImageIcon(getClass().getResource("folder16.png"));

        //        filterIcon = new ImageIcon(new ImageIcon("/filterSymbol.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        filterButton = new JButton(filterIcon);
        filterButton.setFont(new Font("Serif",Font.PLAIN, 15));
        filterButton.setForeground(Color.darkGray);
        filterButton.setBounds(xSelection + 250, 367, 40, 40);
        filterButton.addActionListener(new GUI());
        mainPanel.add(filterButton);

        clearRosterButton = new JButton("Clear Roster");
        clearRosterButton.setBounds(xSelection - 5, 460, 100, 40);
        clearRosterButton.addActionListener(new GUI());
        mainPanel.add(clearRosterButton);

        // generate stream
        generateStreamButton.setFont(new Font("Serif",Font.BOLD, 25));
        generateStreamButton.setBackground(Color.blue);
        generateStreamButton.setForeground(Color.white);
        generateStreamButton.setOpaque(true);
        generateStreamButton.setBounds(500, 600, 300, 100);
        generateStreamButton.addActionListener(new GUI());
        mainPanel.add(generateStreamButton);

        // PREVIEW PANEL
        JLabel streamPreviewTitle = new JLabel("Stream Preview");
        streamPreviewTitle.setFont(new Font("Serif",Font.BOLD, 25));
        streamPreviewTitle.setForeground(Color.gray);
        streamPreviewTitle.setBounds(740, 130, 300, 100);
        mainPanel.add(streamPreviewTitle);

        streamPreviewLabel.setFont(new Font("Serif",Font.PLAIN, 15));
        streamPreviewLabel.setForeground(Color.gray);
        streamPreviewLabel.setBounds(740, 220, 500, 350);
        mainPanel.add(streamPreviewLabel);

        streamPreviewPlayerSelectedLabel.setFont(new Font("Serif",Font.PLAIN, 15));
        streamPreviewPlayerSelectedLabel.setForeground(Color.gray);
        streamPreviewPlayerSelectedLabel.setBounds(700, 200, 500, 100);
        mainPanel.add(streamPreviewPlayerSelectedLabel);

        JSeparator separatorPreview = new JSeparator();
        separatorPreview.setBounds(670, 150, 1, 425);
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

        System.out.println(title);
        streamPreviewString += title + "\n\n" +
                "Roster: \n";

        for (int i = 0; i < selectedPlayers.size(); i ++) {
            if (selectedPlayerTypes.size() == selectedPlayers.size()) {
                streamPreviewString += "- " + selectedPlayerTypes.get(i) + ": " + selectedPlayers.get(i) + "\n";
            } else {
                streamPreviewString += "- " + selectedPlayers.get(i) + "\n";

            }
        }

        streamPreviewLabel.setText(streamPreviewString);


    }


}
