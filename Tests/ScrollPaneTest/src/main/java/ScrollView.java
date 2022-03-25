import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class ScrollView {


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(1300, 800));

        int x = 500;
        int y = 100;
        for (int i = 0; i < 30; i ++) {
            JLabel label = new JLabel("test " + i);
            label.setBounds(x, y, 50, 100);
            mainPanel.add(label);
            y += 50;
        }

//        JScrollPane scrollPane = new JScrollPane(mainPanel,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setPreferredSize(new Dimension(300, 300));

        JScrollPane scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(1200,400));
        // Add panel to frame
//        frameConstraints.gridx = 0;
//        frameConstraints.gridy = 1;
//        frameConstraints.weighty = 1;
        frame.setContentPane(scrollPane); // add acrollpane to frame


        frame.pack();
        // default to game selection
        frame.setVisible(true);

    }
}