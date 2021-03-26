package banana;

import javax.swing.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import banana.*;

public class FramesMaker {
    public static void main(String[] args) {
        //
    }

    public static void mainPage(JFrame mainFrame) throws Exception {
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
            mainFrame.add(button);
            mainFrame.setVisible(true);
        }
    }
}
