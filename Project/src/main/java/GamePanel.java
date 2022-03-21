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

    static JLabel player1 = new JLabel("Player 1");
    static JLabel player2 = new JLabel("Player 2");
    static JLabel player3 = new JLabel("Player 3");

    static String[] superSmashPlayerOptions = {"Matthew Chance Lopez","Mason Harder","Jordan Hopper","Noah","Gavin Phillian"};
    static JComboBox player1Dropdown = new JComboBox(superSmashPlayerOptions);

    static JButton generateStreamButton = new JButton("Generate Stream");

    static JLabel streamPreviewTitleLabel = new JLabel("");
    static JLabel streamPreviewPlayerSelectedLabel = new JLabel("");

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

        // Player 1
        // combobox

        JPanel test = new JPanel();
        test.setBounds(50, 300, 200, 200);
        player1.setFont(new Font("Serif",Font.BOLD, 25));
        player1.setForeground(Color.darkGray);
        player1.setBounds(xSelection, 280, 300, 100);
        test.add(player1);

        player1Dropdown.setBounds(xSelection, 340, 300, 100);
        player1Dropdown.addActionListener(new GUI());
        test.add(player1Dropdown);

        JScrollPane scrollPane = new JScrollPane(test);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

//        scrollPane.setBounds(50, 300, 200, 200);

        mainPanel.add(scrollPane);

        // generate stream

//        generateStreamButton = new JButton("Generate Stream");
        generateStreamButton.setFont(new Font("Serif",Font.BOLD, 25));
        generateStreamButton.setBackground(Color.blue);
        generateStreamButton.setForeground(Color.white);
        generateStreamButton.setOpaque(true);
        generateStreamButton.setBounds(500, 500, 300, 100);
        generateStreamButton.addActionListener(new GUI());
        mainPanel.add(generateStreamButton);

        // PREVIEW PANEL
        JLabel streamPreviewLabel = new JLabel("Stream Preview");
        streamPreviewLabel.setFont(new Font("Serif",Font.BOLD, 25));
        streamPreviewLabel.setForeground(Color.gray);
        streamPreviewLabel.setBounds(700, 130, 300, 100);
        mainPanel.add(streamPreviewLabel);

        streamPreviewTitleLabel.setFont(new Font("Serif",Font.PLAIN, 15));
        streamPreviewTitleLabel.setForeground(Color.gray);
        streamPreviewTitleLabel.setBounds(700, 180, 500, 100);
        mainPanel.add(streamPreviewTitleLabel);

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


}
