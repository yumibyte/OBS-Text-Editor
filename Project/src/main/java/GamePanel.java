import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePanel {

    static String font = "Helvetica Neue";
    static JPanel mainPanel = new JPanel();
    static int xSelection = 50;

    // title/headers
    static String gameTitleType = "";
    static JLabel gameTitleLabel = new JLabel("");
    static JButton backButton = new JButton("");;
    static JTextField streamTitleTextField = new JTextField();
    static JLabel streamTitle = new JLabel("");
    static JLabel gameLogoCorner = new JLabel("");

    // season
    JRadioButton preseasonButton;
    JRadioButton mainSeasonButton;
    JRadioButton playoffsButton;
    JRadioButton finalsButton;

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
    static Map<String, String> finalRoster = new HashMap<String, String>();

    GamePanel() {

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);

        mainPanel.setLayout(null);

        JFrame.setDefaultLookAndFeelDecorated(true);



        JLabel largeTitle = new JLabel("OBS Esports Editor");
        largeTitle.setFont(new Font(font,Font.BOLD, 30));
        largeTitle.setForeground(Color.WHITE);
        largeTitle.setBounds(70, 50, 300, 100);
        mainPanel.add(largeTitle);

        JSeparator separator = new JSeparator();
        separator.setBounds(50, 150, 1200, 1);
        Border borderSeparator = BorderFactory.createLineBorder(Color.BLACK, 5);
        separator.setBorder(borderSeparator);
        separator.setOrientation(SwingConstants.HORIZONTAL);
        mainPanel.add(separator);

        // top right label
        gameTitleLabel.setFont(new Font(font,Font.BOLD, 30));
        gameTitleLabel.setForeground(Color.WHITE);
        gameTitleLabel.setBounds(900, 50, 300, 100);
        mainPanel.add(gameTitleLabel);

        // top right logo
        gameLogoCorner.setBounds(1150, 50, 150, 70);
        mainPanel.add(gameLogoCorner);
        // CONTENTS
        // topmost label above title input
        streamTitle.setFont(new Font(font,Font.BOLD, 25));
        streamTitle.setForeground(Color.darkGray);
        streamTitle.setBounds(xSelection, 130, 300, 100);
        mainPanel.add(streamTitle);

        streamTitleTextField.setBounds(xSelection, 220, 500, 70);
        streamTitleTextField.addActionListener(new GUI());
        mainPanel.add(streamTitleTextField);

        // header
        backButton.setBounds(50, 30, 70, 40);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        ImageIcon backArrowImage = new ImageIcon(new ImageIcon((getClass().getResource("BackArrowImage.png"))).getImage().getScaledInstance(15, 20, Image.SCALE_DEFAULT));
        backButton.setIcon(backArrowImage);
        backButton.addActionListener(new GUI());
        mainPanel.add(backButton);

        // banner
        JLabel banner = new JLabel("");
        ImageIcon bannerIcon = new ImageIcon(getClass().getResource("BackgroundEsports.png"));
        banner.setIcon(bannerIcon);
        banner.setBounds(50, 25, 1200, 120);
        mainPanel.add(banner);

        // Season Selection
        JLabel seasonLabel = new JLabel("Season");
        seasonLabel.setFont(new Font(font,Font.BOLD, 25));
        seasonLabel.setForeground(Color.darkGray);
        seasonLabel.setBounds(xSelection, 275, 300, 100);
        mainPanel.add(seasonLabel);

        preseasonButton = new JRadioButton("Preseason");
        preseasonButton.setBounds(xSelection, 350, 100, 50);
        preseasonButton.addActionListener(new GUI());
        mainPanel.add(preseasonButton);

        mainSeasonButton = new JRadioButton("Main Season");
        mainSeasonButton.setBounds(xSelection + 105, 350, 100, 50);
        mainSeasonButton.addActionListener(new GUI());
        mainPanel.add(mainSeasonButton);

        playoffsButton = new JRadioButton("Playoffs");
        playoffsButton.setBounds(xSelection + 225, 350, 100, 50);
        playoffsButton.addActionListener(new GUI());
        mainPanel.add(playoffsButton);

        finalsButton = new JRadioButton("Finals");
        finalsButton.setBounds(xSelection + 320, 350, 100, 50);
        finalsButton.addActionListener(new GUI());
        mainPanel.add(finalsButton);

        // make selecting only 1 button at a time possible
        ButtonGroup group = new ButtonGroup();
        group.add(preseasonButton);
        group.add(mainSeasonButton);
        group.add(playoffsButton);
        group.add(finalsButton);

        // Roster selection
        JLabel rosterSelectionLabel = new JLabel("Roster Selection");
        rosterSelectionLabel.setFont(new Font(font,Font.BOLD, 25));
        rosterSelectionLabel.setForeground(Color.darkGray);
        rosterSelectionLabel.setBounds(xSelection, 380, 300, 100);
        mainPanel.add(rosterSelectionLabel);

        // roster selection
        // OW and LoL require playerTypes
        rosterSelectionPlayerTypeLabel = new JLabel("Player type");
        rosterSelectionPlayerTypeLabel.setFont(new Font(font, Font.PLAIN, 17));
        rosterSelectionPlayerTypeLabel.setForeground(Color.darkGray);
        rosterSelectionPlayerTypeLabel.setBounds(xSelection + 300, 435, 300, 100);
        mainPanel.add(rosterSelectionPlayerTypeLabel);

        playerTypeDropdown = new JComboBox(GUI.gameBaseFunctionality.availablePlayerTypes.toArray());
        playerTypeDropdown.setBounds(xSelection + 300, 500, 300, 40);
        playerTypeDropdown.addActionListener(new GUI());
        mainPanel.add(playerTypeDropdown);

        JLabel rosterSelectionPlayerNameLabel = new JLabel("Player name");
        rosterSelectionPlayerNameLabel.setFont(new Font(font,Font.PLAIN, 17));
        rosterSelectionPlayerNameLabel.setForeground(Color.darkGray);
        rosterSelectionPlayerNameLabel.setBounds(xSelection, 435, 300, 100);
        mainPanel.add(rosterSelectionPlayerNameLabel);

        playerDropdown = new JComboBox(GUI.gameBaseFunctionality.availablePlayers.toArray());
        playerDropdown.setBounds(xSelection - 5, 500, 300, 40);
        playerDropdown.addActionListener(new GUI());
        mainPanel.add(playerDropdown);

        filterIcon = new ImageIcon(new ImageIcon((getClass().getResource("filterSymbol.png"))).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        filterButton = new JButton(filterIcon);
        filterButton.setFont(new Font(font,Font.PLAIN, 15));
        filterButton.setForeground(Color.darkGray);
        filterButton.setBounds(xSelection + 252, 457, 40, 40);
        filterButton.addActionListener(new GUI());
        mainPanel.add(filterButton);

        clearRosterButton = new JButton("Clear Roster");
        clearRosterButton.setBounds(xSelection - 5, 560, 120, 40);
        clearRosterButton.addActionListener(new GUI());
        mainPanel.add(clearRosterButton);

        // generate stream
        generateStreamButton.setFont(new Font(font,Font.BOLD, 25));
        generateStreamButton.setBackground(Color.decode("#77C66E"));
        generateStreamButton.setForeground(Color.white);
        generateStreamButton.setOpaque(true);
        generateStreamButton.setBounds(500, 600, 300, 100);
        generateStreamButton.addActionListener(new GUI());
        mainPanel.add(generateStreamButton);

        // PREVIEW PANEL
        JLabel streamPreviewTitle = new JLabel("Stream Preview");
        streamPreviewTitle.setFont(new Font(font,Font.BOLD, 25));
        streamPreviewTitle.setForeground(Color.gray);
        streamPreviewTitle.setBounds(740, 130, 300, 100);
        mainPanel.add(streamPreviewTitle);

        streamPreviewLabel.setFont(new Font(font,Font.PLAIN, 15));
        streamPreviewLabel.setForeground(Color.gray);
        streamPreviewLabel.setBounds(740, 220, 500, 350);
        mainPanel.add(streamPreviewLabel);

        streamPreviewPlayerSelectedLabel.setFont(new Font(font,Font.PLAIN, 15));
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

        // update title
        String inputTitle = GUI.gamePanel.streamTitleTextField.getText();

        switch (GUI.gameBaseFunctionality.gameType) {
            case "OW (Fenrir)":
                GUI.gamePanel.title = "Oakmont Fenrir vs " + inputTitle + " | " + GUI.seasonInput + " | PlayVS                                  ";
                break;
            case "RL (Ragnarok)":
                GUI.gamePanel.title = "Oakmont Ragnarok vs " + inputTitle + " | " + GUI.seasonInput + " | Varsity Rocket League                 ";
                break;
            case "LoL (Berserkers)":
                GUI.gamePanel.title = "Oakmont Berserkers vs " + inputTitle + " | " + GUI.seasonInput + " | Varsity LoL                         ";
                break;
            case "SSBU (Drakkar)":
                GUI.gamePanel.title = inputTitle + " | " + GUI.seasonInput + " | Oakmont Drakkar | Varsity SSBU                                 ";
                break;
            case "Chess":
                GUI.gamePanel.title = inputTitle + " | " + GUI.seasonInput + " | Chess | Oakmont Esports                                        ";
                break;

        }

        // update roster

        String streamPreviewString = "";
        streamPreviewString += title + "\n\n" +
                "Roster (" + GUI.gameBaseFunctionality.numberOfPlayers + "): \n";

        for (int i = 0; i < selectedPlayers.size(); i ++) {

            // string parse for only last letter of last name
            String[] playerName = selectedPlayers.get(i).split(" ");
            playerName[1] = playerName[1].substring(0, 1);
            String fullName = "";
            for (String name: playerName) {
                fullName += name + " ";
            }

            if (selectedPlayerTypes.size() == selectedPlayers.size()) {
                streamPreviewString += "    \u2022  " + selectedPlayerTypes.get(i) + " | " + fullName + "\n";
                finalRoster.put(fullName, selectedPlayerTypes.get(i));
            } else {
                streamPreviewString += "    \u2022  " + fullName + "\n";
                finalRoster.put(fullName, null);

            }
        }

        streamPreviewLabel.setText(streamPreviewString);


    }


}
