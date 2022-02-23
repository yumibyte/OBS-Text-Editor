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

    public static void main(String[] args) {

        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // default to game selection
        frame.setContentPane(gameSelectionPanel.retrievePanel());
        frame.setVisible(true);

    }

    public void swapToGamePanel() {
        // swap panels
        gamePanel = new GamePanel();
        frame.setContentPane(gamePanel.retrievePanel());
        frame.revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameSelectionPanel.overwatchButton) {

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 6;
            gameBaseFunctionality.gameType = "Overwatch";

            swapToGamePanel();

        }

        if (e.getSource() == gameSelectionPanel.rocketLeagueButton) {

            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 8;
            gameBaseFunctionality.gameType = "Rocket League";

            swapToGamePanel();
        }

        if (e.getSource() == gameSelectionPanel.superSmashButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 1;
            gameBaseFunctionality.gameType = "Super Smash Bros Ultimate";

            swapToGamePanel();
        }

        if (e.getSource() == gameSelectionPanel.leagueOfLegendsButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 5;
            gameBaseFunctionality.gameType = "League of Legends";

            swapToGamePanel();
        }

        if (e.getSource() == gameSelectionPanel.chessButton) {
            //set initial values for gametype
            gameBaseFunctionality.numberOfPlayers = 1;
            gameBaseFunctionality.gameType = "Chess";

            swapToGamePanel();
        }

        if (e.getSource() == gamePanel.backButton) {
            gameSelectionPanel = new GameSelectionPanel();
            frame.setContentPane(gameSelectionPanel.retrievePanel());
            frame.revalidate();
        }

        //
    }
}
