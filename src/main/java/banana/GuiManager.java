package banana;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.Method;
import banana.*;

public class GuiManager {
    final JFrame mainFrame = new JFrame(Config.mainWindowName);

    public static void main(String[] args) {

    }

    public void makeWindow() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainFrame.setSize(Config.wWidth, Config.wHeight);
        mainFrame.setVisible(true);
        makeMenu();
    }

    protected void makeMenu() {
        JMenu menu = new JMenu(Config.menuName);
        JMenuBar menuBar = new JMenuBar();
        for (Map.Entry<String, String> entry: Config.menuButtons.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            final Method action = getMethod(PageMaker.class, value);
            JMenuItem button = new JMenuItem(key);
            menu.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    try {
                        action.invoke(new PageMaker(), mainFrame.getContentPane());
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            });
        }
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);
    }


    protected static Method getMethod(Class classobj, String methodName) {
        try {
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

}
