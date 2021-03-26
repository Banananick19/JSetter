package banana;

import javax.swing.*;
import java.util.List;
import banana.*;

public class FramesMaker {
    public static void main(String[] args) {
        //
    }

    public static void mainPage(JFrame mainFrame) throws Exception {
        RunnerConfig runnerConfig = new RunnerConfig();
        List<String> sections = runnerConfig.getSections();
        int offSet = 20;
        int count = 1;
        for (String key : sections) {
            JButton b = new JButton(key);
            b.setBounds(40,90,85,offSet*count);
            mainFrame.add(b);
            mainFrame.setVisible(true);
        }
    }
}
