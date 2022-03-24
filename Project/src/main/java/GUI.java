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

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
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

        // swap panels
//        gamePanel.retrievePanel().removeAll();
//        gamePanel = new GamePanel();

        if (gameBaseFunctionality.gameType.equals("SSBU (Drakkar)") ||
                gameBaseFunctionality.gameType.equals("RL (Ragnarok)") ||
                gameBaseFunctionality.gameType.equals("Chess")) {
            System.out.println(gameBaseFunctionality.gameType.toString());
//            // no playerType will be created. Shift player name selection upwards
//            yRosterLabel = 335;
//            yRosterLabelDropdown = 400;
//            yFilter = 360;
//            yClearRoster = 450;
            System.out.println("true");
            gamePanel.rosterSelectionPlayerTypeLabel.setVisible(false);
            gamePanel.playerTypeDropdown.setVisible(false);

        } else {
            System.out.println("false");

            gamePanel.rosterSelectionPlayerTypeLabel.setVisible(true);
            gamePanel.playerTypeDropdown.setVisible(true);


        }
        mainPanel = gamePanel.retrievePanel();
        mainPanel.updateUI();
        ArrayList<String> noFilter = new ArrayList<String>();
        noFilter.add("None");
        gameBaseFunctionality.determineAvailablePlayers(noFilter);


        // TODO create scroll pane

        frame.setContentPane(mainPanel);

        frame.revalidate();
        frame.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameSelectionPanel.overwatchButton) {

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 6;
            gamePanel.gameTitleLabel.setText("Overwatch");
            gameBaseFunctionality.gameType = "OW (Fenrir)";
            gamePanel.streamTitle.setText("Opposing Team Name");
            swapToGamePanel();

        }

        else if (e.getSource() == gameSelectionPanel.rocketLeagueButton) {

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 8;
            gamePanel.gameTitleLabel.setText("Rocket League");
            gameBaseFunctionality.gameType = "RL (Ragnarok)";
            gamePanel.streamTitle.setText("Opposing Team Name");

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.superSmashButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 1;
            gamePanel.gameTitleLabel.setText("Super Smash Bros");
            gameBaseFunctionality.gameType = "SSBU (Drakkar)";
            gamePanel.streamTitle.setText("Player Name");

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.leagueOfLegendsButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 5;
            gamePanel.gameTitleLabel.setText("League of Legends");
            gameBaseFunctionality.gameType = "LoL (Berserkers)";
            gamePanel.streamTitle.setText("Opposing Team Name");

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.chessButton) {
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

            String inputTitle = gamePanel.streamTitleTextField.getText();

            switch (gameBaseFunctionality.gameType) {
                case "OW (Fenrir)":
                    gamePanel.title = "Oakmont Fenrir vs " + inputTitle + " | PlayVS                                  ";
                    break;
                case "RL (Ragnarok)":
                    gamePanel.title = "Oakmont Ragnarok vs " + inputTitle + " | Varsity Rocket League                 ";
                    break;
                case "LoL (Berserkers)":
                    gamePanel.title = "Oakmont Berserkers vs " + inputTitle + " | Varsity LoL                         ";
                    break;
                case "SSBU (Drakkar)":
                    gamePanel.title = inputTitle + " | Oakmont Drakkar | Varsity SSBU                                 ";
                    break;
                case "Chess":
                    gamePanel.title = inputTitle + " | Chess | Oakmont Esports                                        ";
                    break;

            }
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
    }
}
