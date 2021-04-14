package banana;

import javax.swing.*;
import java.util.*;
import java.io.File;
import java.awt.Container;
import java.awt.event.*;
import java.awt.BorderLayout;
import banana.*;

public class PageMaker {
    public static File[] updataFiles;
    public static void main(String[] args) {
        //
    }

    public void clearWindow(Container mainFrame) throws Exception {
        mainFrame.removeAll();
        mainFrame.repaint();
        mainFrame.revalidate();
    }

    public void updateWindow(Container mainFrame) throws Exception {
        mainFrame.repaint();
        mainFrame.revalidate();
    }

    public void configsPage(Container mainFrame) throws Exception {
        clearWindow(mainFrame);
        final ConfigsManager configsManager = new ConfigsManager();
        List<String> sections = configsManager.getSections();
        int offSet = 20;
        int count = 1;
        for (final String key : sections) {
            JButton button = new JButton(key);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    try {
                        configsManager.runConfig(key);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            });
            mainFrame.add(BorderLayout.CENTER, button);
        }
        updateWindow(mainFrame);
    }

    public void configsMakePage(Container mainFrame) throws Exception {
        clearWindow(mainFrame);
        final ConfigsManager configsManager = new ConfigsManager();
        JButton chooseFilesButton = new JButton("Choose apps");
        JPanel chooseFilesButtonPanel = new JPanel();
        chooseFilesButtonPanel.add(chooseFilesButton);
        mainFrame.add(chooseFilesButtonPanel);
        chooseFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setMultiSelectionEnabled(true);
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fileChooser.showOpenDialog(mainFrame);
                    updataFiles = fileChooser.getSelectedFiles();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });
        final JTextField configNameTextField = new JTextField(30);
        JPanel configNameTextFieldPanel = new JPanel();
        configNameTextFieldPanel.add(configNameTextField);
        mainFrame.add(configNameTextFieldPanel);
        JButton submitButton = new JButton("Submit");
        JPanel submitButtonPanel = new JPanel();
        submitButtonPanel.add(submitButton);
        mainFrame.add(submitButtonPanel);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    String[] filesPaths = new String[updataFiles.length];
                    int i = 0;
                    for (File file : updataFiles) {
                        filesPaths[i] = file.getAbsolutePath();
                        i++;
                    }
                    configsManager.makeConfig(configNameTextField.getText(), filesPaths);
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });
        updateWindow(mainFrame);

    }
}
