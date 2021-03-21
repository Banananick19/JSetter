package banana;

import org.ini4j.*;
import java.io.*;
import java.util.Map;


public class RunnerConfig {
    protected static String default_config_path = "D:\\config.ini";


    public static void main(String[] args) {
        //
    }


    public static void run_config(String config_name ) throws Exception {
        ConfigParser config  = new ConfigParser();
        config.read(new File(default_config_path));
        for (String key: config.options(config_name)) {
            System.out.println("cmd /c start \"\" " + "\"" + config.get(config_name, key) + "\"");
            Process proc = Runtime.getRuntime().exec("cmd /c start \"\" " + "\"" + config.get(config_name, key) + "\"");
        }

    }

    public static void make_config(String config_name, String[] apps) {
        //
    }

    public static void append_to_config(String config_name, String[] apps) throws Exception {
        int index = 0;
        ConfigParser config  = new ConfigParser();
        config.read(new InputStreamReader(new FileInputStream(new File(default_config_path)), "UTF-8"));
        for (String key : config.options(config_name)) index++;


        for (String app: apps) {
            config.set(config_name, String.valueOf(index), app);
            index++;
        }
        config.write(new File(default_config_path));
    }

    public static void remove_config(String config_name) {
        //
    }
}