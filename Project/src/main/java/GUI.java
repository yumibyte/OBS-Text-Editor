import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUI {

    public static GameSelectionPanel gameSelectionPanel = new GameSelectionPanel();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Esports OBS Editor");
        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // default to game selection
        frame.setContentPane(gameSelectionPanel.retrievePanel());
        frame.setVisible(true);

    }
}
