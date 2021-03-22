package banana;

import org.ini4j.*;
import java.io.*;
import java.util.Map;


public class RunnerConfig {
    protected static String default_config_path = "D:\\config.ini";
    protected static ConfigParser config = new ConfigParser();

    public static void main(String[] args) {
        //
    }


    public static void runConfig(String config_name ) throws Exception {
        config.read(new File(default_config_path));
        for (String key: config.options(config_name)) {
            System.out.println("cmd /c start \"\" " + "\"" + config.get(config_name, key) + "\"");
            Process proc = Runtime.getRuntime().exec("cmd /c start \"\" " + "\"" + config.get(config_name, key) + "\"");
        }

    }

    public static void makeConfig(String config_name, String[] apps) throws Exception {
        config.read(new File(default_config_path));
        config.addSection(config_name);

        for(int i = 0; i<apps.length; i++) {
            config.set(config_name, String.valueOf(i), apps[i]);
        }
        config.write(new File(default_config_path));
    }

    public static void appendToConfig(String config_name, String[] apps) throws Exception {
        int index = 0;
        config.read(new File(default_config_path));
        for (String key : config.options(config_name)) index++;


        for (String app : apps) {
            config.set(config_name, String.valueOf(index), app);
            index++;
        }
        config.write(new File(default_config_path));
    }

    public static void removeConfig(String config_name) throws Exception {
        config.read(new File(default_config_path));
        config.removeSection(config_name);
        config.write(new File(default_config_path));
    }

    public static void removeOption(String config_name, String option) throws Exception {
        config.read(new File(default_config_path));
        config.removeOption(config_name, option);
        config.write(new File(default_config_path));
    }
    public static void renameConfig(String old_config_name, String new_config_name) throws Exception {
        config.read(new File(default_config_path));
        config.addSection(new_config_name);
        for (String key : config.options(old_config_name)) {
            config.set(new_config_name, key, config.get(old_config_name, key));
        }
        removeConfig(old_config_name);
        config.write(new File(default_config_path));
    }
}