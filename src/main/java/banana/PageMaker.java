package banana;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.File;
import java.awt.Container;
import java.awt.event.*;
import java.awt.BorderLayout;

import banana.*;

public class PageMaker {
    protected File[] updataFiles;
    protected String updataConfigName;
    final ConfigsManager configsManager = new ConfigsManager();

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
        List<String> sections = configsManager.getSections();
        int offSet = 20;
        int count = 1;
        for (final String key : sections) {
            JButton button = new JButton(key);
            button.setFont(Config.mainFont);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
        JButton chooseFilesButton = new JButton("Choose apps");
        chooseFilesButton.setFont(Config.mainFont);
        JPanel chooseFilesButtonPanel = new JPanel();
        chooseFilesButtonPanel.add(chooseFilesButton);
        mainFrame.add(chooseFilesButtonPanel);
        chooseFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        configNameTextField.setFont(Config.mainFont);
        JPanel configNameTextFieldPanel = new JPanel();
        configNameTextFieldPanel.add(configNameTextField);
        mainFrame.add(configNameTextFieldPanel);
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(Config.mainFont);
        JPanel submitButtonPanel = new JPanel();
        submitButtonPanel.add(submitButton);
        mainFrame.add(submitButtonPanel);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if ((updataFiles == null) | (configNameTextField.getText().length() == 0)) {
                        JOptionPane.showMessageDialog(mainFrame.getParent(),
                                Config.errorMakeMessage,
                                Config.errorMakeTitle, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
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

    public void configsUpdatePage(Container mainFrame) throws Exception {
        clearWindow(mainFrame);
        JButton chooseFilesButton = new JButton("Choose apps");
        chooseFilesButton.setFont(Config.mainFont);
        JPanel chooseFilesButtonPanel = new JPanel();
        chooseFilesButtonPanel.add(chooseFilesButton);
        mainFrame.add(chooseFilesButtonPanel);
        chooseFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        JList configsList = new JList(configsManager.getSections().toArray(new String[configsManager.getSections().size()]));
        configsList.setPrototypeCellValue("1234567");
        mainFrame.add(new JScrollPane(configsList));
        configsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) { // Игнорируем событие mouseDown
                    // Получаем выбранное значение
                    String val = configsList.getSelectedValue().toString();
                    System.out.println(val);
                    // Устанавливаем полученное значение в текстовое поле
                    updataConfigName = val;
                }
            }
        });
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(Config.mainFont);
        JPanel submitButtonPanel = new JPanel();
        submitButtonPanel.add(submitButton);
        mainFrame.add(submitButtonPanel);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(updataConfigName);
                    if ((updataFiles == null) | (updataConfigName.length() == 0)) {
                        JOptionPane.showMessageDialog(mainFrame.getParent(),
                                Config.errorMakeMessage,
                                Config.errorMakeTitle, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String[] filesPaths = new String[updataFiles.length];
                    int i = 0;
                    for (File file : updataFiles) {
                        filesPaths[i] = file.getAbsolutePath();
                        i++;
                    }
                    configsManager.appendToConfig(updataConfigName, filesPaths);
                } catch (Exception ex) {
                    System.out.println("updatePage");
                    System.out.println(ex);
                }

            }
        });
        updateWindow(mainFrame);

    }
}
