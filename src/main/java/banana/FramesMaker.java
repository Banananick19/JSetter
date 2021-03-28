package banana;

import javax.swing.*;
import java.util.*;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import banana.*;

public class FramesMaker {
    public static File[] updataFiles;
    public static void main(String[] args) {
        //
    }

    public static void mainPage(JPanel mainPanel, JFrame mainFrame) throws Exception {
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        final RunnerConfig runnerConfig = new RunnerConfig();
        List<String> sections = runnerConfig.getSections();
        int offSet = 20;
        int count = 1;
        for (final String key : sections) {
            JButton button = new JButton(key);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    try {
                        runnerConfig.runConfig(key);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            });
            button.setBounds(40,90,85,offSet*count);
            mainPanel.add(button);

        }
    }

    public static void configMaker(JPanel mainPanel, JFrame mainFrame) throws Exception {
        final RunnerConfig runnerConfig = new RunnerConfig();
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        JButton chooseFilesButton = new JButton("Choose apps");
        chooseFilesButton.setBounds(20, 40, 20, 40);
        chooseFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setMultiSelectionEnabled(true);
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fileChooser.showOpenDialog(GuiMaker.mainFrame);
                    updataFiles = fileChooser.getSelectedFiles();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });
        mainPanel.add(chooseFilesButton);
        final JTextField configNameTextField = new JTextField();
        configNameTextField.setBounds(60, 80, 60, 80);
        mainPanel.add(configNameTextField);
        JButton submitButton = new JButton();
        submitButton.setBounds(100, 120, 100, 120);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    String[] filesPaths = new String[updataFiles.length];
                    int i = 0;
                    for (File file : updataFiles) {
                        filesPaths[i] = file.getAbsolutePath();
                        i++;
                    }
                    runnerConfig.makeConfig(configNameTextField.getText(), filesPaths);
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });
        mainPanel.add(submitButton);

    }
}
