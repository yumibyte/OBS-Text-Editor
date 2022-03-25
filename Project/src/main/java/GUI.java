import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI implements ActionListener {
    static GameSelectionPanel gameSelectionPanel = new GameSelectionPanel();

    static GamePanel gamePanel = new GamePanel();
    static GameBase.GameBaseFunctionality gameBaseFunctionality = new GameBase.GameBaseFunctionality();

    static JFrame frame = new JFrame("Esports OBS Editor");
    static JPanel mainPanel = new JPanel();

    static String seasonInput = "";

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame.setSize(1320, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // default to game selection
        mainPanel = gameSelectionPanel.retrievePanel();
        frame.setContentPane(mainPanel);
        mainPanel.updateUI();
        frame.setVisible(true);



    }

    public void swapToGamePanel() {

        // reset text field of stream preview
        gamePanel.selectedPlayerTypes.clear();
        gamePanel.selectedPlayers.clear();
        gamePanel.title = "";
        gamePanel.finalRoster.clear();
        gamePanel.updateStreamPreview();

        // swap panels

        if (gameBaseFunctionality.gameType.equals("SSBU (Drakkar)") ||
                gameBaseFunctionality.gameType.equals("RL (Ragnarok)") ||
                gameBaseFunctionality.gameType.equals("Chess")) {
            gamePanel.rosterSelectionPlayerTypeLabel.setVisible(false);
            gamePanel.playerTypeDropdown.setVisible(false);

        } else {
            gamePanel.rosterSelectionPlayerTypeLabel.setVisible(true);
            gamePanel.playerTypeDropdown.setVisible(true);

        }

        mainPanel = gamePanel.retrievePanel();

        mainPanel.updateUI();
        ArrayList<String> noFilter = new ArrayList<String>();
        noFilter.add("None");
        gameBaseFunctionality.determineAvailablePlayers(noFilter);

        frame.setContentPane(mainPanel);
        frame.revalidate();
        frame.repaint();

        mainPanel.revalidate();
        mainPanel.repaint();


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameSelectionPanel.overwatchButton) {

            // set logo GamePanel
            ImageIcon gameLogoIcon = new ImageIcon(new ImageIcon((getClass().getResource("OverwatchLogo.png"))).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            gamePanel.gameLogoCorner.setIcon(gameLogoIcon);

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 6;
            gamePanel.gameTitleLabel.setText("Overwatch");
            gameBaseFunctionality.gameType = "OW (Fenrir)";
            gamePanel.streamTitle.setText("Opposing Team Name");
            swapToGamePanel();

        }

        else if (e.getSource() == gameSelectionPanel.rocketLeagueButton) {

            // set logo GamePanel
            ImageIcon gameLogoIcon = new ImageIcon(new ImageIcon((getClass().getResource("RocketLeagueLogo.png"))).getImage().getScaledInstance(120, 70, Image.SCALE_DEFAULT));
            gamePanel.gameLogoCorner.setIcon(gameLogoIcon);

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 3;
            gamePanel.gameTitleLabel.setText("Rocket League");
            gameBaseFunctionality.gameType = "RL (Ragnarok)";
            gamePanel.streamTitle.setText("Opposing Team Name");

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.superSmashButton) {

            // set logo GamePanel
            ImageIcon gameLogoIcon = new ImageIcon(new ImageIcon((getClass().getResource("SuperSmashBrosLogo.png"))).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            gamePanel.gameLogoCorner.setIcon(gameLogoIcon);
            frame.repaint();
            frame.revalidate();

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 1;
            gamePanel.gameTitleLabel.setText("Super Smash Bros");
            gameBaseFunctionality.gameType = "SSBU (Drakkar)";
            gamePanel.streamTitle.setText("Player Name");

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.leagueOfLegendsButton) {

            // set logo GamePanel
            ImageIcon gameLogoIcon = new ImageIcon(new ImageIcon((getClass().getResource("LeagueOfLegendsLogo.png"))).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            gamePanel.gameLogoCorner.setIcon(gameLogoIcon);

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 5;
            gamePanel.gameTitleLabel.setText("League of Legends");
            gameBaseFunctionality.gameType = "LoL (Berserkers)";
            gamePanel.streamTitle.setText("Opposing Team Name");

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.chessButton) {

            // set logo GamePanel
            ImageIcon gameLogoIcon = new ImageIcon(new ImageIcon((getClass().getResource("ChessLogo.png"))).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            gamePanel.gameLogoCorner.setIcon(gameLogoIcon);

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 1;
            gamePanel.gameTitleLabel.setText("Chess");
            gameBaseFunctionality.gameType = "Chess";
            gamePanel.streamTitle.setText("Player Name");

            swapToGamePanel();
        }

        else if (e.getSource() == gamePanel.backButton) {

            frame.setContentPane(gameSelectionPanel.retrievePanel());

            frame.revalidate();
            frame.repaint();

        }

        else if (e.getSource() == gamePanel.streamTitleTextField) {

            gamePanel.updateStreamPreview();
        }

        else if (e.getSource() == gamePanel.playerDropdown) {
            gamePanel.selectedPlayers.add(gamePanel.playerDropdown.getSelectedItem().toString());
            gamePanel.updateStreamPreview();
        }

        else if (e.getSource() == gamePanel.generateStreamButton) {
            try {
                gameBaseFunctionality.generateStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        else if (e.getSource() == gamePanel.playerTypeDropdown)  {
            gamePanel.selectedPlayerTypes.add(gamePanel.playerTypeDropdown.getSelectedItem().toString());
            gamePanel.updateStreamPreview();
        }

        else if (e.getSource() == gamePanel.clearRosterButton) {

            gamePanel.selectedPlayerTypes.clear();
            gamePanel.selectedPlayers.clear();
            gamePanel.finalRoster.clear();

            gamePanel.updateStreamPreview();
        }

        else if (e.getSource() == gamePanel.filterButton) {
            // prompt for type of filtering
            String[] filterOptions = {"Alphabetically", "Team Assignment"};
            JList list = new JList(filterOptions);
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            JOptionPane.showMessageDialog(null, new JScrollPane(list), "Filtering Options", JOptionPane.QUESTION_MESSAGE, gamePanel.filterIcon);
            ArrayList<String> selectedFilters = new ArrayList<String>();

            for (Object value: list.getSelectedValues()) {
                selectedFilters.add(value.toString());
            }

            gameBaseFunctionality.determineAvailablePlayers(selectedFilters);
        }

        else if (e.getSource() == gamePanel.preseasonButton) {
            seasonInput = "Preseason";
            gamePanel.updateStreamPreview();
        }

        else if (e.getSource() == gamePanel.mainSeasonButton) {
            seasonInput = "Main Season";
            gamePanel.updateStreamPreview();
        }

        else if (e.getSource() == gamePanel.playoffsButton) {
            seasonInput = "Playoffs";
            gamePanel.updateStreamPreview();
        }

        else if (e.getSource() == gamePanel.finalsButton) {
            seasonInput = "Finals";
            gamePanel.updateStreamPreview();
        }
    }
}
