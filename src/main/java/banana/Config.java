package banana;

import java.io.File;
import java.nio.file.Paths;
import java.net.URLDecoder;

import banana.*;

public class Config {
    final static int wWidth = 400;
    final static int wHeight = 400;
    final static String ConfigPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "configs.ini"; // получение пути до Main
    final static ConfigsManager config = new ConfigsManager();

    public static void verifyFiles() {
            try {
                File ConfigFile = new File(ConfigPath);
                if (ConfigFile.createNewFile()) {
                    System.out.println("Configs file created");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
}