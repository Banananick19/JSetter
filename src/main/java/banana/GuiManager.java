package banana;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.Method;

public class GuiManager extends JFrame {

    public static void main(String[] args) {

    }

    public GuiManager() {
        super(Config.mainWindowName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(Config.wWidth, Config.wHeight);
        makeMenu();
    }

    protected void makeMenu() {
        JMenu menu = new JMenu(Config.menuName);
        JMenuBar menuBar = new JMenuBar();
        PageMaker pageMaker = new PageMaker();
        Container contentPane = getContentPane();
        for (Map.Entry<String, String> entry : Config.menuButtons.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            final Method action = getMethod(PageMaker.class, value);
            JMenuItem button = new JMenuItem(key);
            menu.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        action.invoke(pageMaker, contentPane);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            });
        }
        menuBar.add(menu);
        setJMenuBar(menuBar);
        setVisible(true);
    }


    protected static Method getMethod(Class classobj, String methodName) {
        try {
            Method[] methods = classobj.getMethods();

            for (Method method : methods) {

                if (method.getName().equals(methodName)) {
                    return method;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
