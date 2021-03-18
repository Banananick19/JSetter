package banana;

import org.ini4j.*;
import java.io.*;


public class RunnerConfig {
    protected static String default_config_path = "D:\\config.ini";

    public static void main(String[] args) throws IOException {
        //
    }
    public void run_config(String config_name) throws IOException {
        Wini configs_ini = new Wini(new File(default_config_path));

        for (int app_number = 0;;app_number++) {
            String app_path = configs_ini.get(config_name, String.valueOf(app_number));
            if (app_path != null) {
                String command = "cmd /c start " + app_path;
                Process proc = Runtime.getRuntime().exec(command);
            } else {
                break;
            }
        }
    }
    public void make_config(String config_name, String[] apps_dirs) throws IOException {
        Wini configs_ini = new Wini(new File(default_config_path));

        for (int i = 0; i < apps_dirs.length; i++) {
            configs_ini.put(config_name, String.valueOf(i), apps_dirs[i]);
        }
        configs_ini.store();
    }

    public void remove_config(String config_name) throws IOException {
        Wini configs_ini = new Wini(new File(default_config_path));
        configs_ini.remove(configs_ini.get(config_name));
        configs_ini.store();
    }
}