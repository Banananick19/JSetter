package banana;

import javax.swing.*;
import java.util.*;
import java.io.File;
import java.awt.Container;
import java.awt.event.*;
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
            button.setBounds(10, offSet*count, 100, 30);
            mainFrame.add(button);
        }
    }

    public void configsMakePage(Container mainFrame) throws Exception {
        clearWindow(mainFrame);
        final ConfigsManager configsManager = new ConfigsManager();
        JButton chooseFilesButton = new JButton("Choose apps");
        chooseFilesButton.setBounds(10, 10, 100, 20);
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
        mainFrame.add(chooseFilesButton);
        final JTextField configNameTextField = new JTextField();
        configNameTextField.setBounds(10, 40, 100, 30);
        mainFrame.add(configNameTextField);
        JButton submitButton = new JButton();
        submitButton.setBounds(10, 80, 100, 30);
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
        mainFrame.add(submitButton);

    }
}
