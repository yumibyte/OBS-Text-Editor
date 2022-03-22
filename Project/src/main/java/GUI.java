import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    static GamePanel gamePanel = new GamePanel();
    static GameSelectionPanel gameSelectionPanel = new GameSelectionPanel();
    static GameBase.GameBaseFunctionality gameBaseFunctionality = new GameBase.GameBaseFunctionality();
    static JFrame frame = new JFrame("Esports OBS Editor");
    static JPanel mainPanel = new JPanel();

    public static void main(String[] args) {

        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // default to game selection
        mainPanel = gameSelectionPanel.retrievePanel();
        frame.setContentPane(mainPanel);
        frame.setVisible(true);

    }

    public void swapToGamePanel() {
        // swap panels

        mainPanel = gamePanel.retrievePanel();

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
            gameBaseFunctionality.gameType = "Overwatch";
            gamePanel.streamTitle.setText("Opposing Team Name");
            swapToGamePanel();

        }

        else if (e.getSource() == gameSelectionPanel.rocketLeagueButton) {

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 8;
            gamePanel.gameTitleLabel.setText("Rocket League");
            gameBaseFunctionality.gameType = "Rocket League";
            gamePanel.streamTitle.setText("Opposing Team Name");

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.superSmashButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 1;
            gamePanel.gameTitleLabel.setText("Super Smash Bros");
            gameBaseFunctionality.gameType = "Super Smash Bros";
            gamePanel.streamTitle.setText("Player Name");

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.leagueOfLegendsButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 5;
            gamePanel.gameTitleLabel.setText("League of Legends");
            gameBaseFunctionality.gameType = "League of Legends";
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

            System.out.println("back");
            frame.setContentPane(gameSelectionPanel.retrievePanel());
            frame.revalidate();
            frame.repaint();
        }

        else if (e.getSource() == gamePanel.streamTitleTextField) {

            String inputTitle = gamePanel.streamTitleTextField.getText();

            switch (gameBaseFunctionality.gameType) {
                case "Overwatch":
                    gamePanel.title = "Oakmont Fenrir vs " + inputTitle + " | PlayVS                                  ";
                    break;
                case "Rocket League":
                    gamePanel.title = "Oakmont Ragnarok vs " + inputTitle + " | Varsity Rocket League                 ";
                    break;
                case "League of Legends":
                    gamePanel.title = "Oakmont Berserkers vs " + inputTitle + " | Varsity LoL                         ";
                    break;
                case "Super Smash Bros":
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
            gameBaseFunctionality.generateStream();

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
    }
}
