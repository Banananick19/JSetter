package banana;

import java.util.*;
import java.io.File;
import java.awt.Font;
import java.nio.file.Paths;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class Config {
    // window settings
    final static int wWidth = 400;
    final static int wHeight = 400;
    final static String mainWindowName = "JSetter";
    final static Font mainFont = new Font("Arial", Font.BOLD, 20);
    // end window settings
    // messages
    final static String errorMakeTitle = "Error";
    final static String errorMakeMessage = "Fill the data fieds";
    // end messages
    // mainMenu
    final static String menuName = "file";
    final static Map<String, String> menuButtons = Map.ofEntries(
            Map.entry("configs", "configsPage"),
            Map.entry("make", "configsMakePage"),
            Map.entry("update", "configsUpdatePage")
    );
    //end mainMenu
    final static String ConfigPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "configs.ini"; // получение пути до Main
    final static ConfigsManager config = new ConfigsManager();

    public static void ready() { // must be manual run before run other programm code
        verifyFiles();
    }

    public static void verifyFiles() {
        try {
            File ConfigFile = new File(ConfigPath);
            if (ConfigFile.createNewFile()) {
                System.out.println("Configs file created");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}