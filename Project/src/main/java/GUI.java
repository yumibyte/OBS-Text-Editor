import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    static GameSelectionPanel gameSelectionPanel = new GameSelectionPanel();
    static GameBase.GameBaseFunctionality gameBaseFunctionality = new GameBase.GameBaseFunctionality();
    static GamePanel gamePanel = new GamePanel();
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
        gamePanel = new GamePanel();
        mainPanel = gamePanel.retrievePanel();

        // TODO create scroll pane

        frame.setContentPane(mainPanel);
        mainPanel.revalidate();
        frame.repaint();



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameSelectionPanel.overwatchButton) {

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 6;
            gameBaseFunctionality.gameType = "Overwatch";
            gamePanel.gameTitleType = "Opposing Team Name";

            swapToGamePanel();

        }

        else if (e.getSource() == gameSelectionPanel.rocketLeagueButton) {

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 8;
            gameBaseFunctionality.gameType = "Rocket League";
            gamePanel.gameTitleType = "Opposing Team Name";

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.superSmashButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 1;
            gameBaseFunctionality.gameType = "Super Smash Bros";
            gamePanel.gameTitleType = "Player Name";

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.leagueOfLegendsButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 5;
            gameBaseFunctionality.gameType = "League of Legends";
            gamePanel.gameTitleType = "Opposing Team Name";

            swapToGamePanel();
        }

        else if (e.getSource() == gameSelectionPanel.chessButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 1;
            gameBaseFunctionality.gameType = "Chess";
            gamePanel.gameTitleType = "Player Name";

            swapToGamePanel();
        }

        else if (e.getSource() == gamePanel.backButton) {

            frame.setContentPane(gameSelectionPanel.retrievePanel());
            frame.revalidate();
            frame.repaint();
        }

        else if (e.getSource() == gamePanel.streamTitleTextField) {
            gamePanel.streamPreviewTitleLabel.setText("Title: Super Smash Bros - Drakkar - " + gamePanel.streamTitleTextField.getText());
        }

        else if (e.getSource() == gamePanel.player1Dropdown) {
//            System.out.println()
            gamePanel.player1Dropdown.setSelectedIndex(gamePanel.player1Dropdown.getSelectedIndex());
            gamePanel.streamPreviewPlayerSelectedLabel.setText("Roster: " + gamePanel.player1Dropdown.getSelectedItem());
        }

        else if (e.getSource() == gamePanel.generateStreamButton) {
            gameBaseFunctionality.generateStream("Title: Super Smash Bros - Drakkar - " + gamePanel.streamTitleTextField.getText());
        }


    }
}
