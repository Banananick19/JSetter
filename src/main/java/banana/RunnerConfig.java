package banana;

import org.ini4j.*;
import java.io.*;

public class RunnerConfig {
    public static void main(String args[]) throws IOException {
    //
    }
    public void run_config(String config_name) throws IOException {
        String default_config_path = "D:\\config.ini";
        Wini configs_base = new Wini(new File(default_config_path));
        int app_number = 0;
        System.out.println("cmd /c start " + configs_base.get(config_name, String.valueOf(app_number)));
        String command = "cmd /c start " + configs_base.get(config_name, String.valueOf(app_number));
        Process proc = Runtime.getRuntime().exec(command);
    }
}