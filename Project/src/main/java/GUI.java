import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    public static GameSelectionPanel gameSelectionPanel = new GameSelectionPanel();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Esports OBS Editor");
        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // default to game selection
        frame.setContentPane(gameSelectionPanel.retrievePanel());
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameSelectionPanel.overwatchButton) {
            System.out.println("OW");
        }

        if (e.getSource() == gameSelectionPanel.rocketLeagueButton) {
            System.out.println("RL");
        }

        if (e.getSource() == gameSelectionPanel.superSmashButton) {
            System.out.println("SSB");
        }

        if (e.getSource() == gameSelectionPanel.leagueOfLegendsButton) {
            System.out.println("LOL");
        }

        if (e.getSource() == gameSelectionPanel.chessButton) {
            System.out.println("CH");
        }

        //
    }
}
