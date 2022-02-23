import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel {

    static JPanel mainPanel = new JPanel();
    static int xSelection = 50;

    GamePanel() {

        mainPanel.setLayout(null);

        // header
        JLabel largeTitle = new JLabel("OBS Esports Editor");
        largeTitle.setFont(new Font("Serif",Font.BOLD, 30));
        largeTitle.setBounds(60, 20, 300, 100);
        mainPanel.add(largeTitle);

        JSeparator separator = new JSeparator();
        separator.setBounds(50, 100, 1200, 1);
        Border borderSeparator = BorderFactory.createLineBorder(Color.BLACK, 5);
        separator.setBorder(borderSeparator);
        separator.setOrientation(SwingConstants.HORIZONTAL);
        mainPanel.add(separator);

        // contents
        JLabel streamTitle = new JLabel("Stream Title");
        streamTitle.setFont(new Font("Serif",Font.BOLD, 30));
        streamTitle.setBounds(xSelection, 100, 300, 100);
        mainPanel.add(streamTitle);
    }

    public JPanel retrievePanel() {
        return mainPanel;
    }

}
