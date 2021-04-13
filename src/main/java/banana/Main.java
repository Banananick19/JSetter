package banana;

import banana.*;

public class Main {
    public static void main(String[] args) {
        Config.ready();
        System.out.println(Config.ConfigPath);
        System.out.println(Config.menuButtons);
        GuiManager guiManager = new GuiManager();
        guiManager.makeWindow();
    }
}