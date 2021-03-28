package banana;

import java.util.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.lang.reflect.Method;
import banana.*;

public class GuiMaker {

    public static JFrame mainFrame = new JFrame("Setter");
    private static JPanel widgetsPanel = new JPanel();
    private static RunnerConfig runnerConfig = new RunnerConfig();
    private static List<JComponent> labels = new ArrayList<JComponent>(); // contains elements in frame; need for delete elements from frame for make new page.
    private static Map<String, String> menuButtons = new HashMap<>();


    public static void main(String[] args) throws Exception {
        { // здесь кАроче кнопки менюшки
            menuButtons.put("configs", "mainPage");
            menuButtons.put("make", "configMaker");
        }
        createGui();
    }

    public  static void createGui() throws Exception {
        mainFrame.setSize(400,400);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        createMenu();
    }

    protected static void createMenu() throws Exception {
        JMenu menu = new JMenu("options");
        JMenuBar menuBar = new JMenuBar();
        widgetsPanel.setBounds(0, 0, 400, 400);
        widgetsPanel.setBackground(Color.GREEN);
        mainFrame.add(widgetsPanel);
        for (Map.Entry<String, String> entry: menuButtons.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            JMenuItem button = new JMenuItem(key);
            menu.add(button);
            final FramesMaker framesMaker = new FramesMaker();
            final Method action = getMethod(value);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    try {
                        action.invoke(framesMaker, widgetsPanel, mainFrame);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            });
        }
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);

    }

    protected static Method getMethod(String methodName) {
        try {
            Class classobj = FramesMaker.class;

            Method[] methods = classobj.getMethods();

            for (Method method : methods) {

                if (method.getName().equals(methodName)) {
                    return method;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void makeConfigsFrame() {

    }
}