import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ScrollView
{
    // Field members
    static JPanel panel = new JPanel();
    static Integer indexer = 1;
    static List<JLabel> listOfLabels = new ArrayList<JLabel>();
    static List<JTextField> listOfTextFields = new ArrayList<JTextField>();

    public static void main(String[] args)
    {
        // Construct frame
        JFrame mainFrame = new JFrame();
        mainFrame.setLayout(null);
        mainFrame.setPreferredSize(new Dimension(990, 990));
        mainFrame.setTitle("My Example");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel frame = new JPanel();
        frame.setLayout(new GridBagLayout());
        frame.setPreferredSize(new Dimension(990, 990));

        // Frame constraints
        GridBagConstraints frameConstraints = new GridBagConstraints();

        // Construct button
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ButtonListener());

        // Add button to frame
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 0;
        frame.add(addButton, frameConstraints);

        // Construct panel
        //panel.setPreferredSize(new Dimension(600, 600)); // No need for panel as it will get added to scrollpane
        panel.setLayout(new GridBagLayout());
        panel.setBorder(LineBorder.createBlackLineBorder());


        JScrollPane scrollPane = new JScrollPane(panel,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        // Add panel to frame
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 1;
        frameConstraints.weighty = 1;
        frame.add(scrollPane, frameConstraints); // add acrollpane to frame

        mainFrame.add(frame);
        // Pack frame
        mainFrame.pack();

        // Make frame visible
        mainFrame.setVisible(true);
    }

    static class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            // Clear panel
            panel.removeAll();

            // Create label and text field
            JTextField jTextField = new JTextField();
            jTextField.setSize(100, 200);
            listOfTextFields.add(jTextField);
            listOfLabels.add(new JLabel("Label " + indexer));

            // Create constraints
            GridBagConstraints textFieldConstraints = new GridBagConstraints();
            GridBagConstraints labelConstraints = new GridBagConstraints();

            // Add labels and text fields
            for(int i = 0; i < indexer; i++)
            {
                // Text field constraints
                textFieldConstraints.gridx = 1;
                textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
                textFieldConstraints.weightx = 0.5;
                textFieldConstraints.insets = new Insets(10, 10, 10, 10);
                textFieldConstraints.gridy = i;

                // Label constraints
                labelConstraints.gridx = 0;
                labelConstraints.gridy = i;
                labelConstraints.insets = new Insets(10, 10, 10, 10);

                // Add them to panel
                panel.add(listOfLabels.get(i), labelConstraints);
                panel.add(listOfTextFields.get(i), textFieldConstraints);
            }

            // Align components top-to-bottom
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = indexer;
            c.weighty = 1;
            panel.add(new JLabel(), c);

            // Increment indexer
            indexer++;
            panel.updateUI();
        }
    }
}